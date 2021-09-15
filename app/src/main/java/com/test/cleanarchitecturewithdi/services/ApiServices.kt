package com.test.cleanarchitecturewithdi.services

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiServices {

    @POST("authentication")
    fun userAuthentication() : Call<JsonObject>
}