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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RestaurantDetailsScreen(restaurant: Restaurant, onBack: () -> Unit, onMap: () -> Unit, onEdit: () -> Unit, onDelete: () -> Unit) {
    var confirmDelete by rememberSaveable { mutableStateOf(false) }
    var sharePreview by rememberSaveable { mutableStateOf(false) }
    ScreenLayout("Restaurant Details", onBack) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            SectionLabel("SAVED TO YOUR JOURNAL")
            Text(restaurant.name, style = MaterialTheme.typography.headlineLarge)
            RatingStars(restaurant.rating)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) { restaurant.tags.forEach { TagChip(it) } }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionLabel("ADDRESS")
                    Text(restaurant.address.ifBlank { "No address added" })
                    HorizontalDivider()
                    SectionLabel("PHONE")
                    Text(restaurant.phone.ifBlank { "No phone added" })
                }
            }
            SectionLabel("ABOUT THIS PLACE")
            Text(restaurant.description.ifBlank { "No description added yet." })
            Button(onClick = onMap, modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp)) { Text("Map & Directions") }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onEdit, modifier = Modifier.weight(1f).heightIn(min = 48.dp)) { Text("Edit") }
                OutlinedButton(onClick = { sharePreview = true }, modifier = Modifier.weight(1f).heightIn(min = 48.dp)) { Text("Share") }
            }
            TextButton(onClick = { confirmDelete = true }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)) { Text("Delete Restaurant") }
        }
    }
    if (confirmDelete) AlertDialog(onDismissRequest = { confirmDelete = false }, title = { Text("Delete restaurant?") }, text = { Text("Remove ${restaurant.name} from your saved restaurants?") }, confirmButton = { TextButton(onClick = { confirmDelete = false; onDelete() }) { Text("Delete") } }, dismissButton = { TextButton(onClick = { confirmDelete = false }) { Text("Cancel") } })
    if (sharePreview) AlertDialog(onDismissRequest = { sharePreview = false }, title = { Text("Share preview") }, text = { Text("${restaurant.name}\n${restaurant.address}\nRated ${restaurant.rating}/5 on DineTrack.\n\nSharing is a prototype preview for this UI milestone.") }, confirmButton = { TextButton(onClick = { sharePreview = false }) { Text("Done") } })
}
