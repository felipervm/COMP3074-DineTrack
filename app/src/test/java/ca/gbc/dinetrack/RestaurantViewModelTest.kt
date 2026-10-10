package ca.gbc.dinetrack

import ca.gbc.dinetrack.model.Restaurant
import ca.gbc.dinetrack.viewmodel.RestaurantViewModel
import org.junit.Assert.*
import org.junit.Test

class RestaurantViewModelTest {
    @Test fun addEditDeletePreserveOtherRestaurants() {
        val state = RestaurantViewModel()
        val original = state.restaurants
        val id = state.save(Restaurant(0, " New place ", "Toronto", "", "", listOf("Pizza"), 4))
        assertEquals(4, state.restaurants.size)
        assertEquals("New place", state.find(id)?.name)
        state.save(state.find(id)!!.copy(name = "Edited", rating = 5))
        assertEquals(4, state.restaurants.size)
        assertEquals("Edited", state.find(id)?.name)
        state.delete(id)
        state.delete(id)
        assertEquals(original, state.restaurants)
    }
    @Test(expected = IllegalArgumentException::class)
    fun rejectBlankName() {
        RestaurantViewModel().save(Restaurant(0, "  ", "", "", "", emptyList(), 3))
    }
}
