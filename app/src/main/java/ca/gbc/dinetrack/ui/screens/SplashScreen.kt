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
fun SplashScreen(onFinished: () -> Unit) {
    LaunchedEffect(Unit) { kotlinx.coroutines.delay(1400); onFinished() }
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        Column(Modifier.fillMaxSize().padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            val plateColor = MaterialTheme.colorScheme.onPrimary
            androidx.compose.foundation.Canvas(Modifier.size(96.dp)) {
                drawCircle(plateColor, radius = size.minDimension * .34f, style = androidx.compose.ui.graphics.drawscope.Stroke(3.dp.toPx()))
                drawCircle(plateColor.copy(alpha = .5f), radius = size.minDimension * .25f, style = androidx.compose.ui.graphics.drawscope.Stroke(2.dp.toPx()))
                val left = size.width * .06f
                drawLine(plateColor, androidx.compose.ui.geometry.Offset(left, size.height * .2f), androidx.compose.ui.geometry.Offset(left, size.height * .8f), 3.dp.toPx())
                for (i in -1..1) drawLine(plateColor, androidx.compose.ui.geometry.Offset(left + i * 6.dp.toPx(), size.height * .2f), androidx.compose.ui.geometry.Offset(left + i * 6.dp.toPx(), size.height * .4f), 2.dp.toPx())
                drawLine(plateColor, androidx.compose.ui.geometry.Offset(size.width * .94f, size.height * .2f), androidx.compose.ui.geometry.Offset(size.width * .94f, size.height * .8f), 4.dp.toPx())
            }
            Spacer(Modifier.height(24.dp))
            Text("DineTrack", style = MaterialTheme.typography.headlineLarge)
            Spacer(Modifier.height(12.dp))
            Text("Your personal restaurant guide")
            Spacer(Modifier.height(48.dp))
            CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(24.dp))
        }
    }
}
