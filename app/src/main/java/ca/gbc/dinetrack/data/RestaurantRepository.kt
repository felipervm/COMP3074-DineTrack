package ca.gbc.dinetrack.data

import ca.gbc.dinetrack.model.Restaurant

class RestaurantRepository(private val dao: RestaurantDao) {
    val restaurants = dao.observeAll()

    suspend fun save(restaurant: Restaurant): Long {
        val item = normalize(restaurant)
        return if (item.id == 0L) dao.insert(item) else {
            dao.update(item)
            item.id
        }
    }

    suspend fun delete(id: Long) = dao.delete(id)

    companion object {
        fun normalize(restaurant: Restaurant): Restaurant {
            require(restaurant.name.isNotBlank())
            return restaurant.copy(name = restaurant.name.trim(), rating = restaurant.rating.coerceIn(1, 5))
        }
    }
}
