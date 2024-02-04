package com.example.countrydemo.data.model

data class CountryList(
    val flags: Flags,
    val coatOfArms: CoatOfArms,
    val name: Name,
    val capital: ArrayList<String>,
    val latlng :ArrayList<Long>,
    val area: Long,
    val maps: Maps,
    val population:Long,
    val timezones: ArrayList<String>
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

