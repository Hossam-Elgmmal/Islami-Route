package com.route.islami.api

import com.route.islami.adapters.model.RadiosResponse
import retrofit2.Call
import retrofit2.http.GET

interface RadioService {

    @GET("radios")
    fun getRadios(): Call<RadiosResponse>

}