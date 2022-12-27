package com.example.assignmenttemplate.api

import retrofit2.Response
import retrofit2.http.GET

interface TrendingMoviesApi {
    @GET("trending/movie/week?api_key=a7d26cbde2c4cc637772c32299352a9f")
    suspend fun getTrendingMovies(): Response<ResponsePage>
}