package cz.martingabriel.meteorapp

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import cz.martingabriel.meteorapp.model.MeteoriteLandingInfo

@Composable
fun MeteoriteLandingDetailScreen (meteoriteLanding: MeteoriteLandingInfo) {
    var scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    MeteoriteLandingDetailHeader(meteoriteLanding = meteoriteLanding, containerHeight = this@BoxWithConstraints.maxHeight)
                    MeteoriteLandingDetailContent(meteoriteLanding = meteoriteLanding, containerHeight = this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}

@Composable
private fun MeteoriteLandingDetailHeader(
    meteoriteLanding: MeteoriteLandingInfo,
    containerHeight: Dp
) {
    val landingCoords = LatLng(meteoriteLanding.geolocation.latitude.toDouble(), meteoriteLanding.geolocation.longitude.toDouble())
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(landingCoords, 2f)
    }
    var uiSettings by remember { mutableStateOf(MapUiSettings(
        zoomControlsEnabled = false,
        scrollGesturesEnabled = false,
        zoomGesturesEnabled = false
    )) }
    var properties by remember { mutableStateOf(MapProperties(mapType = MapType.TERRAIN))}

    GoogleMap(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth(),
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings,
        properties = properties
    ) {
        Marker(
            state = MarkerState(position = landingCoords),
            title = meteoriteLanding.name
        )
    }
}

@Composable
private fun MeteoriteLandingDetailContent(meteoriteLanding: MeteoriteLandingInfo, containerHeight: Dp) {
    Column {
        ContentTitle(meteoriteLanding = meteoriteLanding)
        ContentProperty(label = "Year", value = getShortYear(meteoriteLanding.year))
        ContentProperty(label = "Country", value = meteoriteLanding.geolocation.countryName)
        ContentProperty(label = "Meteorite classification", value = meteoriteLanding.recclass)
        ContentProperty(label = "Mass", value = getMassString(meteoriteLanding.mass))
        ContentProperty(label = "Geolocation", value = getGeolocationString(meteoriteLanding.geolocation))
        Spacer(modifier = Modifier.height((containerHeight - 100.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun ContentTitle(meteoriteLanding: MeteoriteLandingInfo) {
    Column (
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = meteoriteLanding.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ContentProperty(label: String, value: String) {
    Column( modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp) ) {
        Divider( modifier = Modifier.padding(bottom = 4.dp))
        Text(
            text = label,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = value,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Visible
        )
    }
}
