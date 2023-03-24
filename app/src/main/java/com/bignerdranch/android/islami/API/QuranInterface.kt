package com.bignerdranch.android.islami.API

import com.bignerdranch.android.islami.models.QuranList
import retrofit2.Call
import retrofit2.http.GET

interface QuranInterface {
    @GET("01?language=en")
    fun  getQuran(): Call<QuranList>
}