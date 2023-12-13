package com.example.homework17_leacture20.model


data class Person(val email:String,
                  val password:String) {
}
data class RequestResponse(
    var email: String?,
    val token: String?
)