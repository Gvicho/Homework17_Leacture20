package com.example.homework17_leacture20

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkAPI {

    @POST("login")
    suspend fun loginUser(@Body person: Person): Response<RequestResponse>

    @POST("register")
    suspend fun registerUser(@Body person: Person): Response<RequestResponse>

}