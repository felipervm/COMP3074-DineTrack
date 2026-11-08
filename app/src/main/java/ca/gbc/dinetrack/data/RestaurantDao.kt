package ca.gbc.dinetrack.data

import androidx.room.*
import ca.gbc.dinetrack.model.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants ORDER BY id")
    fun observeAll(): Flow<List<Restaurant>>

    @Insert
    suspend fun insert(restaurant: Restaurant): Long

    @Update
    suspend fun update(restaurant: Restaurant)

    @Query("DELETE FROM restaurants WHERE id = :id")
    suspend fun delete(id: Long)
}
