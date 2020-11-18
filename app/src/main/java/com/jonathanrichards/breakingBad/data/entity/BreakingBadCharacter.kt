package com.jonathanrichards.breakingBad.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BreakingBadCharacter(
    @SerializedName("char_id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("occupation") val occupation: List<String>,
    @SerializedName("img") val characterImage: String,
    @SerializedName("status") val status: String?,
    @SerializedName("nickname") val nickname: String?,
    @SerializedName("appearance") val seasonAppearance: List<Int>?
) : Serializable