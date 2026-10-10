package ca.gbc.dinetrack.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import ca.gbc.dinetrack.data.MockRestaurantData
import ca.gbc.dinetrack.model.Restaurant

class RestaurantViewModel : ViewModel() {
    private val entries = mutableStateListOf<Restaurant>().apply { addAll(MockRestaurantData.restaurants) }
    val restaurants: List<Restaurant> get() = entries.toList()
    private var nextId = 4L
    fun find(id: Long) = entries.firstOrNull { it.id == id }
    fun save(restaurant: Restaurant): Long {
        require(restaurant.name.isNotBlank())
        val item = restaurant.copy(name = restaurant.name.trim(), rating = restaurant.rating.coerceIn(1, 5))
        val index = entries.indexOfFirst { it.id == item.id }
        return if (index >= 0) { entries[index] = item; item.id }
        else { val added = item.copy(id = nextId++); entries.add(added); added.id }
    }
    fun delete(id: Long) { entries.removeAll { it.id == id } }
}
