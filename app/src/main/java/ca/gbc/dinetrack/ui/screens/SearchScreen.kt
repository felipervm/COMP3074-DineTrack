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
fun SearchScreen(restaurants: List<Restaurant>, onOpen: (Restaurant) -> Unit, onBack: () -> Unit) {
    var query by rememberSaveable { mutableStateOf("") }
    var selectedTag by rememberSaveable { mutableStateOf<String?>(null) }
    val tags = restaurants.flatMap { it.tags }.distinct().sorted()
    val results = restaurants.filter { restaurant ->
        (restaurant.name.contains(query.trim(), true) || restaurant.tags.any { it.contains(query.trim(), true) }) &&
            (selectedTag == null || restaurant.tags.contains(selectedTag))
    }
    ScreenLayout("Search & Filter", onBack) { padding ->
        androidx.compose.foundation.lazy.LazyColumn(Modifier.fillMaxSize().padding(padding), contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item { Text("Find your next revisit", style = MaterialTheme.typography.headlineMedium) }
            item { OutlinedTextField(query, { query = it }, label = { Text("Restaurant name or tag") }, singleLine = true, modifier = Modifier.fillMaxWidth()) }
            item { Text("Filter by tag", style = MaterialTheme.typography.titleMedium) }
            item { androidx.compose.foundation.lazy.LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item { FilterChip(selected = selectedTag == null, onClick = { selectedTag = null }, label = { Text("All tags") }) }
                items(tags.size) { index -> FilterChip(selected = selectedTag == tags[index], onClick = { selectedTag = if (selectedTag == tags[index]) null else tags[index] }, label = { Text(tags[index]) }) }
            } }
            item { Text("${results.size} restaurants", style = MaterialTheme.typography.labelLarge) }
            if (results.isEmpty()) item { EmptyState("No matches yet", "Try another restaurant name or tag, or choose All tags.") }
            items(results.size, key = { results[it].id }) { index -> RestaurantCard(results[index]) { onOpen(results[index]) } }
        }
    }
}
