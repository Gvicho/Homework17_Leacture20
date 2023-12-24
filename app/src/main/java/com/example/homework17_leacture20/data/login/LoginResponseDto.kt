package com.example.homework17_leacture20.data.login

import com.squareup.moshi.Json

data class LoginResponseDto(
    @Json(name = "token")
    val token:String
) {
}