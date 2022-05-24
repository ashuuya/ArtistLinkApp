package com.example.artistlink.Model

data class InfoModel(
    val about: String,
    val image: String,
    val name: String,
    val platforms: MutableList<Platform>,
    val social: MutableList<Social>
)

data class Platform(
    val name: String,
    val url: String
)

data class Social(
    val name: String,
    val url: String
)