package com.example.artistlink.Interface

import com.example.artistlink.Model.InfoModel
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @Headers(
        "X-RapidAPI-Host: musiclinkssapi.p.rapidapi.com",
        "X-RapidAPI-Key: eb194c61cdmsh690666316ffae94p1ca362jsn7379733fcfa0"
    )
    @GET("artist")
    fun getLinkList(@Query("query") query: String): Call<InfoModel>
}