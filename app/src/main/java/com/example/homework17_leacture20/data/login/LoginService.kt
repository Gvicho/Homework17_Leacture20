package com.example.homework17_leacture20.data.login


import com.example.homework17_leacture20.data.common.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login")
    suspend fun loginUser(@Body userRequest: UserRequest): Response<LoginResponseDto>
}