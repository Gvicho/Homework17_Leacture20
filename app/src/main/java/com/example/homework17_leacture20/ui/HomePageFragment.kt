package com.example.homework17_leacture20.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.homework17_leacture20.databinding.FragmentHomePageBinding


class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate) {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    companion object {

        @JvmStatic
        fun newInstance() =
            HomePageFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun setUp() {
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val email = sharedPreferences.getString("Email", "")
        val token = sharedPreferences.getString("Token", "")

        if(email!!.isEmpty()&& token!!.isEmpty()){
            openLoginPage()
        }

        binding.tvEmail.text = email
        binding.button.setOnClickListener{
            with(sharedPreferences.edit()) {
                clear()
                apply()
            }
            openLoginPage()
        }
    }

    override fun onStop() {
        super.onStop()
        val rememberMe = sharedPreferences.getBoolean("Remember",false)

        if(!rememberMe){
            with(sharedPreferences.edit()) {
                clear()
                apply()
            }
        }
    }

    private fun openLoginPage(){
        val action = HomePageFragmentDirections.actionHomePageFragmentToLoginFragment()
        findNavController().navigate(action)
    }
}