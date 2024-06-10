package com.geeks.rickandmortyy.data.model.detail


import com.google.gson.annotations.SerializedName
data class Origin(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)