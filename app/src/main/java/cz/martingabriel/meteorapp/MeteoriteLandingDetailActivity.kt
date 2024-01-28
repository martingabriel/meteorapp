package cz.martingabriel.meteorapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cz.martingabriel.meteorapp.model.MeteoriteLandingInfo
import cz.martingabriel.meteorapp.ui.theme.MeteorAppTheme

class MeteoriteLandingDetailActivity : AppCompatActivity() {

    private val meteoriteLanding: MeteoriteLandingInfo by lazy {
        intent?.getSerializableExtra(METEORITE_LANDING_ID) as MeteoriteLandingInfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}