package com.nihalp01.movies.Network.API


data class Movies(
    val results: List<Result>,
    val MovieCastList: List<Cast>
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
)
data class Cast(
    val character: String,
    val gender: String,
    val name: String,
    val profile_path: String
)