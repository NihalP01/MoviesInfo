package com.nihalp01.movies.Network.API

import java.io.Serializable


data class Movies(
    val results: List<Result>
)

data class Result(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
    val original_language: String,
    val popularity: Number
) : Serializable
