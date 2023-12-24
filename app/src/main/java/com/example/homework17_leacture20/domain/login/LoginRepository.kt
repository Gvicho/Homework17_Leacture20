package com.example.homework17_leacture20.domain.login

import com.example.homework17_leacture20.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow


interface LoginRepository {
    suspend fun logIn(email:String,password:String): Flow<ResultWrapper<LoginResponse>>
}