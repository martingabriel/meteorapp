package cz.martingabriel.meteorapp.api

import cz.martingabriel.meteorapp.model.MeteoriteLandingInfo
import retrofit2.Call
import retrofit2.http.GET

interface NasaMeteoriteLandingsApi {

    companion object {
        const val LANDING_ENDPOINT = "gh4g-9sfh.json"
    }

    @GET(LANDING_ENDPOINT)
    fun getMeteoriteLandingsInfo(): Call<List<MeteoriteLandingInfo>>
}