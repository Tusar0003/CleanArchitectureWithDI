package com.test.cleanarchitecturewithdi.services

import com.test.cleanarchitecturewithdi.models.SignIn
import com.test.cleanarchitecturewithdi.models.SignInResponse
import com.test.cleanarchitecturewithdi.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiServices {

    @POST("authentication")
    fun userAuthentication(@Body signIn: SignIn) : Flow<ApiResponse<SignInResponse>>
}