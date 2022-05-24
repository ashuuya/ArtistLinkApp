package com.example.artistlink.Common

import com.example.artistlink.Interface.RetrofitServices
import com.example.artistlink.Retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://musiclinkssapi.p.rapidapi.com/apiSearch/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}