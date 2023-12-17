package com.example.homework17_leacture20.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {

    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()


    val loginAPI : LoginAPI by lazy {
        retrofit.create(LoginAPI::class.java)
    }

    val registerAPI : RegisterAPI by lazy {
        retrofit.create(RegisterAPI::class.java)
    }

}