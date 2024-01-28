package cz.martingabriel.meteorapp.api

import cz.martingabriel.meteorapp.model.MeteoriteLandingInfo
import retrofit2.Call
import retrofit2.http.GET

interface NasaMeteoriteLandingsApi {

    companion object {
        const val LANDINGS_ENDPOINT = "gh4g-9sfh.json?\$query=SELECT%20name%2C%20id%2C%20nametype%2C%20recclass%2C%20mass%2C%20fall%2C%20year%2C%20reclat%2C%20reclong%2C%20geolocation%20WHERE%20(%60mass%60%20IS%20NOT%20NULL%20)%20ORDER%20BY%20mass%20DESC"
    }

    @GET(LANDINGS_ENDPOINT)
    fun getMeteoriteLandingsInfo(): Call<List<MeteoriteLandingInfo>>
}