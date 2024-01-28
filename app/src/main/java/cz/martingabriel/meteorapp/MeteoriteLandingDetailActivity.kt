package cz.martingabriel.meteorapp

import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cz.martingabriel.meteorapp.model.MeteoriteLandingInfo
import cz.martingabriel.meteorapp.ui.theme.MeteorAppTheme
import java.util.Locale

class MeteoriteLandingDetailActivity : AppCompatActivity() {

    private lateinit var geocoder: Geocoder
    private val meteoriteLanding: MeteoriteLandingInfo by lazy {
        intent?.getSerializableExtra(METEORITE_LANDING_ID) as MeteoriteLandingInfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // get state name from Geocoder API
        getStateName(meteoriteLanding)

        setContent {
            MeteorAppTheme {
                MeteoriteLandingDetailScreen(meteoriteLanding = meteoriteLanding)
            }
        }
    }

    companion object {
        private const val METEORITE_LANDING_ID = "meteorite_id"
        fun newIntent(context: Context, meteoriteLanding: MeteoriteLandingInfo) =
            Intent(context, MeteoriteLandingDetailActivity::class.java).apply {
                putExtra(METEORITE_LANDING_ID, meteoriteLanding)
            }
    }

    fun getStateName(meteoriteLandingInfo: MeteoriteLandingInfo) {
        geocoder = Geocoder(this, Locale.getDefault())

        val result = geocoder.getFromLocation(
            meteoriteLandingInfo.geolocation.latitude.toDouble(),
            meteoriteLandingInfo.geolocation.longitude.toDouble(),
            1)

        if (result != null) {
            meteoriteLandingInfo.geolocation.countryName = result.first().countryName
        }

        Log.i("GeoTest", "StateName: ${meteoriteLandingInfo.geolocation.countryName}")
    }
}