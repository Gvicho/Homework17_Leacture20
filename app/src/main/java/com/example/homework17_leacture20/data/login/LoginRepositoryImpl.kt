package com.example.homework17_leacture20.data.login

import android.util.Log.d
import com.example.homework17_leacture20.data.common.ResultWrapper
import com.example.homework17_leacture20.data.common.UserRequest
import com.example.homework17_leacture20.domain.login.LoginRepository
import com.example.homework17_leacture20.domain.login.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(val loginService: LoginService) : LoginRepository {


    override suspend fun logIn(email: String, password: String): Flow<ResultWrapper<LoginResponse>> {
        d("tag123","In LoginRepository Impl")
        return flow{
            emit(ResultWrapper.Loading(loading = true))
            d("tag123","Loading")
            try {
                val response = loginService.loginUser(UserRequest( email,password))
                if(response.isSuccessful){
                    d("tag123","Success")
                    emit( ResultWrapper.Success( data = response.body()!!.toDomain() ) )
                }else{
                    d("tag123","Error")
                    emit( ResultWrapper.Error(errorMessage = response.errorBody()?.string() ?: "emptyError") )
                }
            }catch (e:Throwable){
                d("exception","${e.message}")
            }
            emit(ResultWrapper.Loading(loading = false))
        }
    }

}