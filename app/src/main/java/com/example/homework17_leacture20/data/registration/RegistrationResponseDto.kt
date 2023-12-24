package com.example.homework17_leacture20.data.registration

import com.squareup.moshi.Json

data class RegistrationResponseDto(
    @Json(name = "id")
    val id:Int,
    @Json(name = "token")
    val token:String
) {
}