package ca.gbc.dinetrack.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.*

@Composable
fun RatingStars(rating: Int, onChange: ((Int) -> Unit)? = null) {
    Row(Modifier.semantics(mergeDescendants = true) { contentDescription = "$rating out of 5 stars" }) {
        (1..5).forEach { star ->
            if (onChange == null) Text(if (star <= rating) "★" else "☆", color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.titleLarge)
            else TextButton(onClick = { onChange(star) }, modifier = Modifier.width(48.dp).semantics { contentDescription = "Rate $star stars"; selected = star == rating }) {
                Text(if (star <= rating) "★" else "☆", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}
