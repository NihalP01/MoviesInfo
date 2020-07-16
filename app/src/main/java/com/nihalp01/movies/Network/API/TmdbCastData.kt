package com.nihalp01.movies.Network.API

import java.io.Serializable

data class CastList(
    val cast: List<Cast>
)

data class Cast(
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val profile_path: String
): Serializable
