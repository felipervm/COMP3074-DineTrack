package ca.gbc.dinetrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ca.gbc.dinetrack.model.Restaurant
import ca.gbc.dinetrack.ui.components.*

@Composable
fun RestaurantListScreen(restaurants: List<Restaurant>, onOpen: (Restaurant) -> Unit, onAdd: () -> Unit, onSearch: () -> Unit, onAbout: () -> Unit) {
    ScreenLayout("DineTrack", actions = { TextButton(onClick = onAbout) { Text("About") } }) { padding ->
        androidx.compose.foundation.lazy.LazyColumn(Modifier.fillMaxSize().padding(padding), contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item {
                SectionLabel("YOUR FOOD JOURNAL")
                Spacer(Modifier.height(8.dp))
                Text("Good places.\nGreat memories.", style = MaterialTheme.typography.headlineLarge)
                Spacer(Modifier.height(8.dp))
                Text("Your saved restaurants · "+restaurants.size, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            item { OutlinedButton(onClick = onSearch, modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp)) { Text("Search restaurants & filter tags") } }
            item { Button(onClick = onAdd, modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp)) { Text("+ Add Restaurant") } }
            if (restaurants.isEmpty()) item { EmptyState("Start your collection", "Save your first restaurant to keep its details close.") }
            items(restaurants.size, key = { restaurants[it].id }) { index -> RestaurantCard(restaurants[index]) { onOpen(restaurants[index]) } }
        }
    }
}
