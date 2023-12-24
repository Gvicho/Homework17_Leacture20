package com.example.homework17_leacture20.data.registration

import com.example.homework17_leacture20.domain.registration.RegistrationResponse

fun RegistrationResponseDto.toDomain() :RegistrationResponse{
    return RegistrationResponse(
        id = id,
        token = token
    )
}