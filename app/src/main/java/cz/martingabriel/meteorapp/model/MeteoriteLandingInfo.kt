package cz.martingabriel.meteorapp.model

import java.io.Serializable

data class MeteoriteLandingInfo (
    val name: String,
    val id: String,
    val nametype: String,
    val recclass: String,
    val mass: String,
    val fall: String,
    val year: String,
    val reclat: String,
    val reclong: String,
    val geolocation: Geolocation
) : Serializable

data class Geolocation(
    val latitude: String,
    val longitude: String,
) : Serializable