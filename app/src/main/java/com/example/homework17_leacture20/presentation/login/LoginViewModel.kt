package com.example.homework17_leacture20.presentation.login


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17_leacture20.data.common.ResultWrapper
import com.example.homework17_leacture20.domain.login.LoginRepository
import com.example.homework17_leacture20.domain.login.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository): ViewModel() {
    private val _response = MutableStateFlow<ResultWrapper<LoginResponse>?>(null)
    val response: StateFlow<ResultWrapper<LoginResponse>?> = _response.asStateFlow()

    fun loginPerson(email:String,password:String){
        viewModelScope.launch {
            loginRepository.logIn(email,password).collect{
                Log.d("tag123","collecting aee")
                when(it){
                    is ResultWrapper.Success -> _response.value = ResultWrapper.Success(data = it.data!!)
                    is ResultWrapper.Error -> _response.value = ResultWrapper.Error(errorMessage = it.errorMessage!!)
                    is ResultWrapper.Loading -> _response.value = ResultWrapper.Loading(loading = it.loading)
                }
            }
        }
    }
}