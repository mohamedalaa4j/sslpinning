package com.example.sslpinning

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("posts/1")
    suspend fun fetchData(): Response<PostResponse>

    @GET("mobile/contact-us")
    suspend fun amtalek(): Response<ContactUsResponse>
}