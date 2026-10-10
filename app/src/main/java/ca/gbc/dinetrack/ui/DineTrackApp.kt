package ca.gbc.dinetrack.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import ca.gbc.dinetrack.navigation.AppScreen
import ca.gbc.dinetrack.ui.screens.*
import ca.gbc.dinetrack.viewmodel.RestaurantViewModel

@Composable
fun DineTrackApp(viewModel: RestaurantViewModel) {
    var screen by rememberSaveable { mutableStateOf(AppScreen.Splash) }
    var selectedId by rememberSaveable { mutableLongStateOf(0L) }
    var editing by rememberSaveable { mutableStateOf(false) }
    var detailsOrigin by rememberSaveable { mutableStateOf(AppScreen.List) }
    val selected = viewModel.find(selectedId)
    fun back() {
        screen = when (screen) {
            AppScreen.Map -> AppScreen.Details
            AppScreen.AddEdit -> if (editing && selected != null) AppScreen.Details else AppScreen.List
            AppScreen.Details -> detailsOrigin
            else -> AppScreen.List
        }
    }
    BackHandler(screen != AppScreen.List && screen != AppScreen.Splash) { back() }
    when (screen) {
        AppScreen.Splash -> SplashScreen { screen = AppScreen.List }
        AppScreen.List -> RestaurantListScreen(viewModel.restaurants,
            onOpen = { selectedId = it.id; detailsOrigin = AppScreen.List; screen = AppScreen.Details },
            onAdd = { editing = false; screen = AppScreen.AddEdit },
            onSearch = { screen = AppScreen.Search }, onAbout = { screen = AppScreen.About })
        AppScreen.Search -> SearchScreen(viewModel.restaurants,
            onOpen = { selectedId = it.id; detailsOrigin = AppScreen.Search; screen = AppScreen.Details }, onBack = { back() })
        AppScreen.AddEdit -> AddEditRestaurantScreen(if (editing) selected else null,
            onSave = { selectedId = viewModel.save(it); screen = if (editing) AppScreen.Details else AppScreen.List }, onCancel = { back() })
        AppScreen.Details -> if (selected != null) RestaurantDetailsScreen(selected,
            onBack = { back() }, onMap = { screen = AppScreen.Map },
            onEdit = { editing = true; screen = AppScreen.AddEdit },
            onDelete = { screen = AppScreen.List; viewModel.delete(selectedId) }) else LaunchedEffect(Unit) { screen = AppScreen.List }
        AppScreen.Map -> if (selected != null) MapScreen(selected) { back() } else LaunchedEffect(Unit) { screen = AppScreen.List }
        AppScreen.About -> AboutScreen { back() }
    }
}
