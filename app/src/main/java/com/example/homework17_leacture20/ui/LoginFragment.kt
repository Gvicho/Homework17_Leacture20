package com.example.homework17_leacture20.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homework17_leacture20.viewmodel.LoginViewModel
import com.example.homework17_leacture20.model.Person
import com.example.homework17_leacture20.model.RequestResponse
import com.example.homework17_leacture20.data.remote.ResultWrapper
import com.example.homework17_leacture20.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var newPerson: Person? = null



    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun setUp() {
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()



        IfSeessionOpenHomePage()

        setListeners()
        collect()
    }


    private fun setListeners(){

        parentFragmentManager.setFragmentResultListener("requestKey", this) { _, bundle ->
            val email = bundle.getString("Email")
            val password = bundle.getString("Password")

            binding.emailEditText.setText(email)
            binding.passwordEditText.setText(password)
        }

        binding.apply {
            loginBtn.setOnClickListener{
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                if(validateAllInput(email,password)){
                    Log.d("tag123","validated!")
                    newPerson = Person(email,password)
                    newPerson?.let {
                        viewModel.loginPerson(it)
                    }
                }
            }

            registerBtn.setOnClickListener {
                openRegistrationPage()
            }
        }

    }

    private fun collect(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.response.collect{ response ->

                    response?.let{
                        if (it is ResultWrapper.Success) {
                            Toast.makeText(context, "successful login", Toast.LENGTH_SHORT).show()
                            val responseBody = response.data

                            responseBody?.let{
                                it.email = newPerson!!.email
                                saveAuthentication(it)
                            }
                            openHomePage()

                        } else if (it is ResultWrapper.Error){
                            Toast.makeText(context, "${it.errorMessage}", Toast.LENGTH_SHORT).show()
                        }else{
                            val loading = it.loading
                            if(loading){
                                binding.progressBar.visibility = View.VISIBLE
                            }else{
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }

                }
            }
        }
    }

    private fun openHomePage(){
        val action = LoginFragmentDirections.actionLoginFragmentToHomePageFragment()
        findNavController().navigate(action)

    }

    private fun openRegistrationPage(){
        val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
        findNavController().navigate(action)

    }

    private fun saveAuthentication(response: RequestResponse){ //response has email and token inside

        val boxWasChecked = binding.rememberMeChkBox.isChecked

        editor.putBoolean("Remember",boxWasChecked)
        editor.putString("Token", response.token)
        editor.putString("Email", response.email)
        editor.commit()
    }

    private fun validateAllInput(email:String,password:String):Boolean{

        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

        if(!email.matches(emailRegex.toRegex())){ //validate email
            Toast.makeText(context, "Email is incorrect", Toast.LENGTH_SHORT).show()
            return false
        }

        if(password.isEmpty()){
            Toast.makeText(context, "enter password please!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun IfSeessionOpenHomePage(){
        if(IfStillHaveToken()){
            openHomePage()
        }
    }

    private fun IfStillHaveToken():Boolean{
        val email = sharedPreferences.getString("Email", "")
        val token = sharedPreferences.getString("Token", "")

        return !(email.isNullOrBlank()&&token.isNullOrBlank())
    }

}