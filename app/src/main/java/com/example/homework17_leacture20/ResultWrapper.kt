package com.example.homework17_leacture20

import android.util.Log
import com.bumptech.glide.load.engine.Resource

sealed class ResultWrapper<T> (
    val data: T? = null,
    val errorMessage: String? = null,
    val loading: Boolean = false
){
    class Success<T>( data: T): ResultWrapper<T>(data = data){
        init {
            Log.d("tag123","new Succsess was initialized")
        }
    }
    class Error<T>(errorMessage:String): ResultWrapper<T>(errorMessage = errorMessage)
    class Loading<T>(loading: Boolean): ResultWrapper<T>(loading = loading)
}