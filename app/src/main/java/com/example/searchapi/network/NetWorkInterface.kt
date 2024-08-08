package com.example.searchapi.network

import com.example.searchapi.data.Documents
import com.example.searchapi.data.Meta
import com.example.searchapi.data.SearchData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val REST_API_KEY = "451c76285cf36962d9e264e04c1a4dd3"

interface NetWorkInterface {
    @Headers("Authorization: KakaoAK ${REST_API_KEY}")
    @GET("image")
    suspend fun getSearchList(
        @Query("query") query: String,
        @Query("size") size: Int = 10
    ): SearchData
}