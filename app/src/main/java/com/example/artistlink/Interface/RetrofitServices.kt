package com.example.artistlink.Interface

import com.example.artistlink.Model.InfoModel
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @Headers(
        "X-RapidAPI-Host: musiclinkssapi.p.rapidapi.com",
        "X-RapidAPI-Key: 656624af09msh62ed6a9b860a0f8p16a3bfjsndbdea8d9fdf6"
    )
    @GET("artist")
    fun getLinkList(@Query("query") query: String): Call<InfoModel>
}