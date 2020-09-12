package com.nihalp01.moviesInfo.Network.API

data class CastDetails(
    val biography: String,
    val birthday: Any,
    val deathday: Any,
    val gender: Int,
    val known_for_department: String,
    val name: String,
    val place_of_birth: Any,
    val popularity: Double,
    val profile_path: Any
)