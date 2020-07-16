package com.nihalp01.movies.Network.API

import java.io.Serializable

data class CastDetails(
    val biography: String,
    val birthday: Any,
    val deathday: Any,
    val gender: Int,
    val homepage: Any,
    val known_for_department: String,
    val name: String,
    val place_of_birth: Any,
    val popularity: Double,
    val profile_path: Any
)