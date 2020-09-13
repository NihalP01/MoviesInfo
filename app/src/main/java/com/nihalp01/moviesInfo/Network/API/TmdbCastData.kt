package com.nihalp01.moviesInfo.Network.API

import java.io.Serializable

data class Cast(
    val cast_id: Int,
    val character: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val birthday: String,
    val profile_path: String
) : Serializable

data class CastList(val cast: List<Cast>)