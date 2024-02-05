package com.example.countrydemo.data.model

data class Country(
    val name: Name,
    val flags: Flags,
    val capital: ArrayList<String>,
    val region: String,
    val maps: Maps,
    val population:Long,
    val timezones: ArrayList<String>,
    val coatOfArms: CoatOfArms,
    val status: String
    )
data class Flags(
    val png: String,
    val svg: String,
    val alt: String
)

data class CoatOfArms(
    val png: String,
    val svg: String
)

data class Name(
    val common: String,
    val official: String
)

data class Maps(
    val googleMaps: String,
    val openStreetMaps:String
)

