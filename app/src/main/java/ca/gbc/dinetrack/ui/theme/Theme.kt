package ca.gbc.dinetrack.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val LightColors = lightColorScheme(
    primary = Forest, onPrimary = Color.White, primaryContainer = Color(0xFFE1EBDD), onPrimaryContainer = Forest,
    secondary = Terracotta, onSecondary = Color.White, secondaryContainer = Color(0xFFFFE5D8), onSecondaryContainer = Color(0xFF703019),
    background = Cream, onBackground = Ink, surface = Cream, onSurface = Ink,
    surfaceVariant = Color(0xFFEDECE2), onSurfaceVariant = Color(0xFF566157), outline = Color(0xFF758175)
)
private val DarkColors = darkColorScheme(
    primary = Color(0xFFA9D2B5), onPrimary = Forest, primaryContainer = Forest,
    secondary = Color(0xFFFFB59B), secondaryContainer = Color(0xFF703019),
    background = Color(0xFF151D18), surface = Color(0xFF151D18), onSurface = Color(0xFFF1F0E8),
    surfaceVariant = Color(0xFF303C33), onSurfaceVariant = Color(0xFFCFD6CB)
)
@Composable
fun DineTrackTheme(darkTheme: Boolean = isSystemInDarkTheme(), dynamicColor: Boolean = false, content: @Composable () -> Unit) {
    val colors = if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (darkTheme) dynamicDarkColorScheme(LocalContext.current) else dynamicLightColorScheme(LocalContext.current)
    } else if (darkTheme) DarkColors else LightColors
    MaterialTheme(colorScheme = colors, typography = Typography,
        shapes = Shapes(small = RoundedCornerShape(12.dp), medium = RoundedCornerShape(20.dp), large = RoundedCornerShape(28.dp)), content = content)
}
