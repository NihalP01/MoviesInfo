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
    @GET("/3/movie/now_playing")
    fun getLatest(@Query("api_key") key: String) : Call<Movies>
    @GET("/3/movie/upcoming")
    fun getUpcoming(@Query("api_key") key: String) : Call<Movies>
    @GET("/3/person/{person_id}")
    fun getCastDetails(@Path("person_id") person_id: Int, @Query("api_key") key: String) : Call<CastDetails>
    @GET("/3/movie/{movie_id}/credits")
    fun getCast( @Path("movie_id") movie_id: Int, @Query("api_key") key: String) : Call<CastList>
    @GET("/3/movie/{movie_id}/videos")
    fun getTrailer(@Path("movie_id") movie_id: Int, @Query("api_key") key: String) : Call<Trailer>
}