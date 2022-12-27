package com.example.assignmenttemplate.api

data class ResponsePage (
    val page: Int,
    val results: Array<ResponseMovie>,
    val total_pages: Int,
    val total_results: Int
)