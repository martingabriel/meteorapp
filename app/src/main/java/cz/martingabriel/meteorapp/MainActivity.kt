package cz.martingabriel.meteorapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.martingabriel.meteorapp.api.NasaMeteoriteLandingsApi
import cz.martingabriel.meteorapp.model.MeteoriteLandingInfo
import cz.martingabriel.meteorapp.ui.theme.MeteorAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {

    private val BASE_URL = "https://data.nasa.gov/resource/"
    private val TAG = "CHECK_REPONSE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeteorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }

        GetAllMeteoritLandings()
    }

    private fun GetAllMeteoritLandings() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaMeteoriteLandingsApi::class.java)

        api.getMeteoriteLandingsInfo().enqueue(object : Callback<List<MeteoriteLandingInfo>>{

            override fun onResponse(
                call: Call<List<MeteoriteLandingInfo>>,
                response: Response<List<MeteoriteLandingInfo>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        for (meteoriteLanding in it) {
                            Log.i(TAG, "onResponse: ${meteoriteLanding.id} - ${meteoriteLanding.name} - ${meteoriteLanding.year}")
                        }
                    }
                }
            }

            override fun onFailure(p0: Call<List<MeteoriteLandingInfo>>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }

        })
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MeteorAppTheme {
        Greeting("Android")
    }
}