package cz.martingabriel.meteorapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import cz.martingabriel.meteorapp.data.DataProvider
import cz.martingabriel.meteorapp.model.MeteoriteLandingInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeteoriteLandingsContent(navigateToProfile: (MeteoriteLandingInfo) -> Unit) {
    val meteoriteLandings = remember { DataProvider.getAllMeteoritLandings() }
    var searchText by remember { mutableStateOf("Search") }
    var searchActive by remember { mutableStateOf(false) }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = meteoriteLandings,
            itemContent = {
                MeteoriteLandingListItem(meteoriteLandingInfo = it, navigateToProfile)
            }
        )
    }
}