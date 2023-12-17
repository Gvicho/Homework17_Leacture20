package com.example.homework17_leacture20.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17_leacture20.data.remote.Network
import com.example.homework17_leacture20.model.Person
import com.example.homework17_leacture20.model.RequestResponse
import com.example.homework17_leacture20.data.remote.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    private val _response = MutableStateFlow<ResultWrapper<RequestResponse>?>(null)
    val response: StateFlow<ResultWrapper<RequestResponse>?> = _response.asStateFlow()

    fun registerPerson(person: Person){
        viewModelScope.launch(Dispatchers.IO) {
            _response.value = ResultWrapper.Loading(loading = true)
            try {
                val response = Network.registerAPI.registerUser(person)
                if(response.isSuccessful){
                    Log.d("tag123","response was successful")
                    _response.value = ResultWrapper.Success(data = response.body()!!)
                    delay(10) // I dont like this here but, probably should have differant flow for events
                }else{
                    _response.value = ResultWrapper.Error(
                        errorMessage = response.errorBody()?.string() ?: "empty Error"
                    )
                    delay(10)
                }
            } catch (e: Throwable) {
                Log.d("tag123", e.toString())
                _response.value =
                    ResultWrapper.Error(errorMessage = e.message ?: "empty Error")
            }
            //this was happening too fast so that I had to wait the data to be emitted for 100 ms
            _response.value = ResultWrapper.Loading(loading = false)
        }
    }
}