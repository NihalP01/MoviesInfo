package com.nihalp01.movies.Network.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbEndpoints {
    @GET("/3/movie/popular")
    fun getMovies(@Query("api_key") key: String): Call<Movies>
    @GET("/3/movie/top_rated")
    fun getTopRated(@Query("api_key") key: String): Call<Movies>
    @GET("/3/movie/latest")
    fun getLatest(@Query("api_key") key: String) : Call<Movies>
    @GET("/3/movie/upcoming")
    fun getUpcoming(@Query("api_key") key: String) : Call<Movies>
    @GET("/movie/{movie_id}/credits")
    fun getCast(@Query("api_key") key: String, @Path("movie_id") movieId: String) : Call<Movies>
}