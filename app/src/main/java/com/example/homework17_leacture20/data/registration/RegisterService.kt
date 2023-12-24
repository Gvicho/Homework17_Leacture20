package com.example.homework17_leacture20.data.registration

import com.example.homework17_leacture20.data.common.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("register")
    suspend fun registerUser(@Body userRequest: UserRequest): Response<RegistrationResponseDto>
}