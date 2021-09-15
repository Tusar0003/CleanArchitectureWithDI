package com.test.cleanarchitecturewithdi.services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

//    private const val BASE_URL = "http://192.168.97.24:8098/api/v1.0/"       // DEV URL
//
//    private var retrofit: Retrofit? = null
//
//    val getClient: Retrofit? get() {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        /*val builder = OkHttpClient.Builder()
//        builder.followRedirects(false)
//        val client = builder.build()*/
//
//        val gSon = GsonBuilder()
//            .setLenient()
//            .create()
//
//        val client = OkHttpClient.Builder()
//            .followRedirects(false)
//            .followSslRedirects(false)
//            .connectTimeout(10, TimeUnit.SECONDS)
//            .readTimeout(10, TimeUnit.SECONDS)
//            .writeTimeout(10, TimeUnit.SECONDS)
//            .addInterceptor(interceptor).build()
//
//        if (retrofit == null) {
//            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gSon))
//                .client(client)
//                .build()
//        }
//
//        return retrofit
//    }
//
//    fun setNewApiClient() {
//        try {
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//            /*val builder = OkHttpClient.Builder()
//            builder.followRedirects(false)
//            val client = builder.build()*/
//
//            val gSon = GsonBuilder()
//                .setLenient()
//                .create()
//
//            val client = OkHttpClient.Builder()
//                .followRedirects(false)
//                .followSslRedirects(false)
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .addInterceptor(interceptor).build()
//
//            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gSon))
//                .client(client)
//                .build()
//        } catch (e: Exception) {
//            throw e
//        }
//    }
}