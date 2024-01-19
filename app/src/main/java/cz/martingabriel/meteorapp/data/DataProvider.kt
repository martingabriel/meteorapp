package cz.martingabriel.meteorapp.data

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import cz.martingabriel.meteorapp.api.NasaMeteoriteLandingsApi
import cz.martingabriel.meteorapp.model.Geolocation
import cz.martingabriel.meteorapp.model.MeteoriteLandingInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataProvider {

    private val BASE_URL = "https://data.nasa.gov/resource/"
    private val TAG = "CHECK_RESPONSE"

    var meteoriteList = mutableStateListOf<MeteoriteLandingInfo>()

    // offline sample data for testing
    val meteoriteListOffline = mutableStateListOf(
        MeteoriteLandingInfo(
            name = "Aachen",
            id = "1",
            nametype = "Valid",
            recclass = "L5",
            mass = "21",
            fall = "Fell",
            year = "1880-01-01T00:00:00.000",
            reclat = "50.775000",
            reclong = "6.083330",
            geolocation = Geolocation(
                latitude = "50.775",
                longitude = "6.08333"
            ),
        ),
        MeteoriteLandingInfo(
            name = "Aarhus",
            id = "2",
            nametype = "Valid",
            recclass = "H6",
            mass = "720",
            fall = "Fell",
            year = "1951-01-01T00:00:00.000",
            reclat = "56.183330",
            reclong = "10.233330",
            geolocation = Geolocation(
                latitude = "56.18333",
                longitude = "10.23333"
            ),
        ),MeteoriteLandingInfo(
            name = "Abee",
            id = "6",
            nametype = "Valid",
            recclass = "EH4",
            mass = "107000",
            fall = "Fell",
            year = "1952-01-01T00:00:00.000",
            reclat = "54.216670",
            reclong = "-113.000000",
            geolocation = Geolocation(
                latitude = "54.21667",
                longitude = "-113.0"
            ),
        ),MeteoriteLandingInfo(
            name = "Acapulco",
            id = "10",
            nametype = "Valid",
            recclass = "Acapulcoite",
            mass = "1914",
            fall = "Fell",
            year = "1976-01-01T00:00:00.000",
            reclat = "16.883330",
            reclong = "-99.900000",
            geolocation = Geolocation(
                latitude = "16.88333",
                longitude = "-99.9"
            ),
        ),
    )
    fun getAllMeteoritLandings() : List<MeteoriteLandingInfo> {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaMeteoriteLandingsApi::class.java)

        api.getMeteoriteLandingsInfo().enqueue(object : Callback<List<MeteoriteLandingInfo>> {

            override fun onResponse(
                call: Call<List<MeteoriteLandingInfo>>,
                response: Response<List<MeteoriteLandingInfo>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        //meteoriteList = it
                        for (meteoriteLanding in it) {
                            // bad data fix
                            if (meteoriteLanding.id != "50693")
                                meteoriteList.add(meteoriteLanding)
                            Log.i(TAG, "onResponse: ${meteoriteLanding.id} - ${meteoriteLanding.name} - ${meteoriteLanding.year}")
                        }
                    }
                }
            }

            override fun onFailure(p0: Call<List<MeteoriteLandingInfo>>, t: Throwable) {
                meteoriteList = meteoriteListOffline
                Log.i(TAG, "onFailure: ${t.message}")
            }

        })

        return meteoriteList
    }
}