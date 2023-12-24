package com.example.homework17_leacture20.data.registration


import android.util.Log
import com.example.homework17_leacture20.data.common.ResultWrapper
import com.example.homework17_leacture20.data.common.UserRequest
import com.example.homework17_leacture20.domain.registration.RegisterRepository
import com.example.homework17_leacture20.domain.registration.RegistrationResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepositoryImpl@Inject constructor(val registerService: RegisterService) :RegisterRepository{


    override suspend fun register(email: String, password: String): Flow<ResultWrapper<RegistrationResponse>> {
        return flow{
            emit(ResultWrapper.Loading(loading = true))
            try {
                val response = registerService.registerUser(UserRequest(email,password))
                if(response.isSuccessful){
                    emit( ResultWrapper.Success( data = response.body()!!.toDomain() ) )
                }else{
                    emit( ResultWrapper.Error(errorMessage = response.errorBody()?.string() ?: "emptyError") )
                }
            }catch (e:Throwable){
                Log.d("exception", "${e.message}")
            }
            emit(ResultWrapper.Loading(loading = false))
        }
    }


}