package ca.gbc.dinetrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import ca.gbc.dinetrack.ui.DineTrackApp
import ca.gbc.dinetrack.ui.theme.DineTrackTheme
import ca.gbc.dinetrack.viewmodel.RestaurantViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val restaurants = ViewModelProvider(this)[RestaurantViewModel::class.java]
        setContent { DineTrackTheme { DineTrackApp(restaurants) } }
    }
}
