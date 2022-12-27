package com.example.assignmenttemplate.api

data class ResponseMovie (
    var poster_path: String,
    var release_date: String,
    var genre_ids: Array<Int>,
    var id: Int,
    var title: String,
    var backdrop_path: String,
    var popularity: Float,
    var vote_average: Float,
    var vote_count: Int,
    var overview: String
)