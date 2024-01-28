package cz.martingabriel.meteorapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import cz.martingabriel.meteorapp.model.MeteoriteLandingInfo
import cz.martingabriel.meteorapp.ui.theme.MeteorAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DataProvider.GetAllMeteoritLandings()
        setContent {
            MeteorAppTheme {
                MyApp {
                    startActivity(MeteoriteLandingDetailActivity.newIntent(this, it))
                }
                // A surface container using the 'background' color from the theme
                /*Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MeteoriteLandingsContent()
                }*/
            }
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(navigateToProfile: (MeteoriteLandingInfo) -> Unit)
{
    Scaffold(
        content = {
            MeteoriteLandingsContent(navigateToProfile = navigateToProfile)
        }
    )
}