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
fun AboutScreen(onBack: () -> Unit) {
    ScreenLayout("About", onBack) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState()).padding(24.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            SectionLabel("MADE FOR MEMORABLE MEALS")
            Text("DineTrack", style = MaterialTheme.typography.headlineLarge)
            Text("Personal Restaurant Guide", style = MaterialTheme.typography.titleLarge)
            Text("Save, organize, rate and rediscover restaurants you have visited or want to remember. Your next great meal starts with a place worth keeping.")
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("COMP3074 – Mobile Application Development I", style = MaterialTheme.typography.titleMedium)
                    HorizontalDivider()
                    Text("Team P 41")
                    Text("Felipe da Rocha Vieira Mattos")
                    Text("Student ID: 101581203")
                    Text("Solo project · Prototype 1 (UI)", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            Text("This prototype uses sample restaurants and in-memory changes. Entries reset when the app process restarts.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
