package com.example.homework17_leacture20.presentation.register

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homework17_leacture20.data.common.ResultWrapper
import com.example.homework17_leacture20.databinding.FragmentRegistrationBinding
import com.example.homework17_leacture20.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()
    private var newRegisterEvent: RegisterEvent? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            RegistrationFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun setUp() {
        setListeners()
        collect()
    }

    private fun setListeners(){

        binding.apply {
            registerBtn.setOnClickListener{
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val passwordRepeat = repeatPasswordEditText.text.toString()

                if(validateAllInput(email,password,passwordRepeat)){
                    Log.d("tag123","validated!")


                    newRegisterEvent = RegisterEvent(email,password)
                    newRegisterEvent?.let {
                        viewModel.registerPerson(it.email,it.password)
                    }

                }
            }
        }

    }
    private fun collect(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.response.collect{ response ->

                    response?.let{
                        if (it is ResultWrapper.Success) {
                            Toast.makeText(context, "successful Registration", Toast.LENGTH_SHORT).show()
                            val responseBody = response.data

                            newRegisterEvent?.let {
                                registerPerson(it)
                            }
                            closeRegistrationFragment()

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

    private fun closeRegistrationFragment() {
        findNavController().popBackStack()
    }


    private fun validateAllInput(email:String,password:String,passwordRepeat:String):Boolean{

        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

        if(!email.matches(emailRegex.toRegex())){ //validate email
            Toast.makeText(context, "Email is incorrect", Toast.LENGTH_SHORT).show()
            return false
        }

        if(password.isEmpty()||passwordRepeat.isEmpty()){
            Toast.makeText(context, "enter password please!", Toast.LENGTH_SHORT).show()
            return false
        }

        if(password!=passwordRepeat){
            Toast.makeText(context, "passwords doesn't match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun registerPerson(newRegisterEvent:RegisterEvent){
        val result = Bundle().apply {
            putString("Email", newRegisterEvent.email)
            putString("Password", newRegisterEvent.password)
        }
        setFragmentResult("requestKey", result)

    }
}