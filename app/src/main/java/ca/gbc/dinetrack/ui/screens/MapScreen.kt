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
fun MapScreen(restaurant: Restaurant, onBack: () -> Unit) {
    var directions by rememberSaveable { mutableStateOf(false) }
    ScreenLayout("Map & Directions", onBack) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            SectionLabel("EXPLORE THE NEIGHBOURHOOD")
            Text(restaurant.name, style = MaterialTheme.typography.headlineMedium)
            Text(restaurant.address.ifBlank { "Add an address to this restaurant." })
            Card(Modifier.fillMaxWidth().height(300.dp)) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    MapIllustration()
                    Surface(shape = MaterialTheme.shapes.medium, color = MaterialTheme.colorScheme.primary) {
                        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("●", style = MaterialTheme.typography.headlineLarge)
                            Text(restaurant.name, style = MaterialTheme.typography.labelLarge)
                            Text("Saved place", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                    Text("TORONTO · MAP PREVIEW", Modifier.align(Alignment.BottomStart).padding(16.dp), style = MaterialTheme.typography.labelSmall)
                }
            }
            Button(onClick = { directions = true }, modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp)) { Text("Get Directions") }
            Text("Map preview only. Live Google Maps, GPS and route guidance are planned for the functional prototype.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
    if (directions) AlertDialog(onDismissRequest = { directions = false }, title = { Text("Directions preview") }, text = { Text("Destination: ${restaurant.address.ifBlank { restaurant.name }}\n\nLive navigation will be available in the functional prototype.") }, confirmButton = { TextButton(onClick = { directions = false }) { Text("Got it") } })
}

@Composable
private fun MapIllustration() {
    val land = MaterialTheme.colorScheme.primaryContainer
    val road = MaterialTheme.colorScheme.surface
    val water = MaterialTheme.colorScheme.secondaryContainer
    androidx.compose.foundation.Canvas(Modifier.fillMaxSize()) {
        drawRect(land)
        drawRect(water, topLeft = androidx.compose.ui.geometry.Offset(size.width * .75f, 0f), size = androidx.compose.ui.geometry.Size(size.width * .25f, size.height))
        for (i in 1..5) {
            val x = size.width * i / 6
            drawLine(road, androidx.compose.ui.geometry.Offset(x, 0f), androidx.compose.ui.geometry.Offset(x - 45f, size.height), strokeWidth = 18.dp.toPx())
        }
        for (i in 1..4) {
            val y = size.height * i / 5
            drawLine(road, androidx.compose.ui.geometry.Offset(0f, y), androidx.compose.ui.geometry.Offset(size.width, y + 35f), strokeWidth = 14.dp.toPx())
        }
    }
}
