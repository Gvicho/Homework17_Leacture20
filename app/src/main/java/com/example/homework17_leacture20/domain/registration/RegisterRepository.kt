package com.example.homework17_leacture20.domain.registration

import com.example.homework17_leacture20.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface RegisterRepository{
    suspend fun register(email:String,password:String): Flow<ResultWrapper<RegistrationResponse>>
}