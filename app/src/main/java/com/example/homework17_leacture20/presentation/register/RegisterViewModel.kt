package com.example.homework17_leacture20.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17_leacture20.data.common.ResultWrapper
import com.example.homework17_leacture20.domain.registration.RegisterRepository
import com.example.homework17_leacture20.domain.registration.RegistrationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel@Inject constructor(private val registerRepository: RegisterRepository): ViewModel() {
    private val _response = MutableStateFlow<ResultWrapper<RegistrationResponse>?>(null)
    val response: StateFlow<ResultWrapper<RegistrationResponse>?> = _response.asStateFlow()

    fun registerPerson(email:String,password:String){
        viewModelScope.launch {
            registerRepository.register(email,password).collect{
                when(it){
                    is ResultWrapper.Success -> _response.value = ResultWrapper.Success(data = it.data!!)
                    is ResultWrapper.Error -> _response.value = ResultWrapper.Error(errorMessage = it.errorMessage!!)
                    is ResultWrapper.Loading -> _response.value = ResultWrapper.Loading(loading = it.loading)
                }
            }
        }
    }
}