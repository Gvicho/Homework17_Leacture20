package com.example.homework17_leacture20.data.remote

import com.example.homework17_leacture20.model.Person
import com.example.homework17_leacture20.model.RequestResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterAPI {
    @POST("register")
    suspend fun registerUser(@Body person: Person): Response<RequestResponse>
}