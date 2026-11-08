package ca.gbc.dinetrack

import ca.gbc.dinetrack.data.RestaurantRepository
import ca.gbc.dinetrack.model.Restaurant
import org.junit.Assert.*
import org.junit.Test

class RestaurantViewModelTest {
    @Test fun normalizePreservesFieldsAndClampsRating() {
        val item = Restaurant(7, " New place ", "Toronto", "123", "Description", listOf("Pizza"), 9)
        assertEquals(item.copy(name = "New place", rating = 5), RestaurantRepository.normalize(item))
        assertEquals(1, RestaurantRepository.normalize(item.copy(rating = 0)).rating)
    }

    @Test(expected = IllegalArgumentException::class)
    fun rejectBlankName() {
        RestaurantRepository.normalize(Restaurant(0, "  ", "", "", "", emptyList(), 3))
    }
}
