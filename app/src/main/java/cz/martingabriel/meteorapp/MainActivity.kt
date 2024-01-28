package cz.martingabriel.meteorapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.martingabriel.meteorapp.model.MeteoriteLandingInfo
import cz.martingabriel.meteorapp.ui.theme.MeteorAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeteorAppTheme {
                MyApp {
                    startActivity(MeteoriteLandingDetailActivity.newIntent(this, it))
                }
            }
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(navigateToProfile: (MeteoriteLandingInfo) -> Unit)
{
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            content = {
                Column() {
                    Row(modifier = Modifier.padding(start = 24.dp, bottom = 16.dp, top = 16.dp, end = 24.dp)) {
                        Image(
                            painter = painterResource(R.drawable.app_icon),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                                .width(30.dp)
                                .height(30.dp)
                                .padding(end = 12.dp)
                        )
                        Text(
                            text = "Meteor",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "App",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    MeteoriteLandingsContent(navigateToProfile = navigateToProfile)
                }
            }
        )
    }
}