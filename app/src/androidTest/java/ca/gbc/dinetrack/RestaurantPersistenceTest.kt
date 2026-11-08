package ca.gbc.dinetrack

import androidx.test.platform.app.InstrumentationRegistry
import ca.gbc.dinetrack.data.DineTrackDatabase
import ca.gbc.dinetrack.data.RestaurantRepository
import ca.gbc.dinetrack.model.Restaurant
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class RestaurantPersistenceTest {
    @Test fun crudSurvivesReopeningAndEmptyDatabaseDoesNotReseed() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val name = "persistence-test.db"
        context.deleteDatabase(name)
        var db = DineTrackDatabase.build(context, name)
        try {
            var repository = RestaurantRepository(db.restaurantDao())
            assertEquals(3, repository.restaurants.first().size)
            val item = Restaurant(0, " Test ", "Toronto", "123", "Notes",
                listOf("Comma, tag", "Quote \" tag", "日本語", ""), 4)
            val id = repository.save(item)
            db.close()
            db = DineTrackDatabase.build(context, name)
            repository = RestaurantRepository(db.restaurantDao())
            assertEquals(item.copy(id = id, name = "Test"), repository.restaurants.first().first { it.id == id })
            repository.save(item.copy(id = id, name = "Edited", rating = 5))
            db.close()
            db = DineTrackDatabase.build(context, name)
            repository = RestaurantRepository(db.restaurantDao())
            assertEquals("Edited", repository.restaurants.first().first { it.id == id }.name)
            repository.restaurants.first().forEach { repository.delete(it.id) }
            db.close()
            db = DineTrackDatabase.build(context, name)
            repository = RestaurantRepository(db.restaurantDao())
            assertTrue(repository.restaurants.first().isEmpty())
            val newId = repository.save(item)
            assertTrue(newId > id)
        } finally {
            db.close()
            context.deleteDatabase(name)
        }
    }
}
