package cz.martingabriel.meteorapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.martingabriel.meteorapp.model.Geolocation
import cz.martingabriel.meteorapp.model.MeteoriteLandingInfo

@Composable
fun MeteoriteLandingListItem(meteoriteLandingInfo: MeteoriteLandingInfo, navigateToProfile: (MeteoriteLandingInfo) -> Unit) {
    Card (
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    )
    {
        Row (
            Modifier.clickable { navigateToProfile(meteoriteLandingInfo) }
        ) {
            Image(
                painter = painterResource(R.drawable.meteorit_icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(84.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )
            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = meteoriteLandingInfo.name, style = typography.headlineSmall)
                Row {
                    getShortYear(meteoriteLandingInfo.year)?.let {
                        Text(text = it, style = typography.bodyMedium)
                    }
                    Text(
                        text = "•",
                        style = typography.bodyMedium,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    )
                    Text(text = meteoriteLandingInfo.recclass, style = typography.bodyMedium)
                }
            }
        }
    }
}

fun getShortYear(date: String?) : String {
    val regex = Regex("^.{0,4}")
    var result: String = "unknown"

    date?.let {
        regex.find(date)?.let { result = it.value }
    }

    return result
}

fun getGeolocationString(geolocation: Geolocation) : String {
    return "Latitude: ${geolocation.latitude} | Longitude: ${geolocation.longitude}"
}

fun getMassString(massInGrams: String): String {
    val massInGramsInt = massInGrams.toFloat().toInt()
    return when {
        massInGramsInt >= 1_000_000 -> "${massInGramsInt / 1_000_000} tons"
        massInGramsInt >= 1_000 -> "${massInGramsInt / 1_000} kg"
        else -> "$massInGramsInt g"
    }
}