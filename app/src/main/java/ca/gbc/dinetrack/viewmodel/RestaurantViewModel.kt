package ca.gbc.dinetrack.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.gbc.dinetrack.data.RestaurantRepository
import ca.gbc.dinetrack.model.Restaurant
import kotlinx.coroutines.launch
import kotlinx.coroutines.CancellationException

class RestaurantViewModel(private val repository: RestaurantRepository) : ViewModel() {
    var restaurants by mutableStateOf<List<Restaurant>>(emptyList())
        private set
    var loaded by mutableStateOf(false)
        private set
    var busy by mutableStateOf(false)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    init {
        viewModelScope.launch {
            try {
                repository.restaurants.collect {
                    restaurants = it
                    loaded = true
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                error = "Unable to load restaurants. Please restart the app."
            }
        }
    }

    fun find(id: Long) = restaurants.firstOrNull { it.id == id }
    fun clearError() { error = null }

    fun save(restaurant: Restaurant, onSaved: (Long) -> Unit) {
        if (busy) return
        busy = true
        viewModelScope.launch {
            try {
                val id = repository.save(restaurant)
                // Keep detail navigation consistent before Room's next Flow emission.
                val item = RestaurantRepository.normalize(restaurant).copy(id = id)
                restaurants = if (restaurants.any { it.id == id })
                    restaurants.map { if (it.id == id) item else it }
                else restaurants + item
                onSaved(id)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                error = "Unable to save restaurant. Please try again."
            } finally { busy = false }
        }
    }

    fun delete(id: Long, onDeleted: () -> Unit) {
        if (busy) return
        busy = true
        viewModelScope.launch {
            try {
                repository.delete(id)
                restaurants = restaurants.filterNot { it.id == id }
                onDeleted()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                error = "Unable to delete restaurant. Please try again."
            } finally { busy = false }
        }
    }
}
