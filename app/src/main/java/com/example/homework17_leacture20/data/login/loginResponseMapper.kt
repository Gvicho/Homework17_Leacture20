package com.example.homework17_leacture20.data.login

import com.example.homework17_leacture20.domain.login.LoginResponse

fun LoginResponseDto.toDomain() : LoginResponse{
    return LoginResponse(
        token = token
    )
}