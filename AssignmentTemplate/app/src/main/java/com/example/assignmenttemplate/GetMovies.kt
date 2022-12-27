package com.example.assignmenttemplate

import android.util.Log
import com.example.assignmenttemplate.api.RetrofitHelper
import com.example.assignmenttemplate.api.TrendingMoviesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetMovies: Thread() {
    //private val apiKey = "a7d26cbde2c4cc637772c32299352a9f"

    public override fun run() {
        println("${Thread.currentThread()} has run.");
        getTrendingMoviesFromAPI()
    }


    // 'https://api.themoviedb.org/3/trending/movie/week?api_key=a7d26cbde2c4cc637772c32299352a9f'
    private fun getTrendingMoviesFromAPI() {
        println("Getting trending movies");

        val TrendingMovies = RetrofitHelper.getInstance().create(TrendingMoviesApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = TrendingMovies.getTrendingMovies()
            Log.d("Movies: ", result.body().toString())
        }
    }
}
