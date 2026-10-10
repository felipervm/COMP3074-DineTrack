package ca.gbc.dinetrack.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenLayout(title: String, back: (() -> Unit)? = null, actions: @Composable RowScope.() -> Unit = {}, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text(title) }, navigationIcon = {
        if (back != null) TextButton(onClick = back) { Text("‹ Back") }
    }, actions = actions) }) { padding -> content(padding) }
}
@Composable
fun SectionLabel(text: String) { Text(text, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.secondary) }
@Composable
fun EmptyState(title: String, message: String) {
    Column(Modifier.fillMaxWidth().padding(vertical = 40.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        Text(message, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
