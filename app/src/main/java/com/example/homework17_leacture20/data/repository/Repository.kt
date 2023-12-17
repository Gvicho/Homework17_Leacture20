package com.example.homework17_leacture20.data.repository

import com.example.homework17_leacture20.data.remote.Network

class Repository {

    private val loginService = Network.loginAPI
    private val registerService = Network.registerAPI

}