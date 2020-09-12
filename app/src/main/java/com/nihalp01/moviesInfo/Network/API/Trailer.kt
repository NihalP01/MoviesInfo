package com.nihalp01.moviesInfo.Network.API

data class Trailerarray(
    val key: String,
    val name: String?
)
data class Trailer(
    val results: List<Trailerarray>
)


