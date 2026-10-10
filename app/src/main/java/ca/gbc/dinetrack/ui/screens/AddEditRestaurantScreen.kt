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
fun AddEditRestaurantScreen(restaurant: Restaurant?, onSave: (Restaurant) -> Unit, onCancel: () -> Unit) {
    var name by rememberSaveable { mutableStateOf(restaurant?.name ?: "") }
    var address by rememberSaveable { mutableStateOf(restaurant?.address ?: "") }
    var phone by rememberSaveable { mutableStateOf(restaurant?.phone ?: "") }
    var description by rememberSaveable { mutableStateOf(restaurant?.description ?: "") }
    var tags by rememberSaveable { mutableStateOf(restaurant?.tags?.joinToString(", ") ?: "") }
    var rating by rememberSaveable { mutableIntStateOf(restaurant?.rating ?: 3) }
    var error by rememberSaveable { mutableStateOf(false) }
    ScreenLayout(if (restaurant == null) "Add Restaurant" else "Edit Restaurant", onCancel) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).imePadding().verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Keep a delicious memory", style = MaterialTheme.typography.headlineMedium)
            Text("The details that make a place worth revisiting.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            OutlinedTextField(name, { name = it; error = false }, label = { Text("Name *") }, isError = error, supportingText = { if (error) Text("Enter a restaurant name") }, singleLine = true, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(address, { address = it }, label = { Text("Address") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(phone, { phone = it }, label = { Text("Phone") }, singleLine = true, keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Phone), modifier = Modifier.fillMaxWidth())
            OutlinedTextField(description, { description = it }, label = { Text("Description") }, minLines = 3, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(tags, { tags = it }, label = { Text("Tags") }, supportingText = { Text("Separate tags with commas, e.g. Thai, Favourite") }, modifier = Modifier.fillMaxWidth())
            SectionLabel("YOUR RATING")
            RatingStars(rating, { rating = it })
            Button(onClick = {
                if (name.isBlank()) error = true
                else onSave(Restaurant(restaurant?.id ?: 0, name.trim(), address.trim(), phone.trim(), description.trim(), tags.split(',').map { it.trim() }.filter { it.isNotEmpty() }.distinct(), rating))
            }, modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp)) { Text("Save Restaurant") }
            OutlinedButton(onClick = onCancel, modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp)) { Text("Cancel") }
        }
    }
}
