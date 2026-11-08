package ca.gbc.dinetrack.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import ca.gbc.dinetrack.model.Restaurant

@Database(entities = [Restaurant::class], version = 1, exportSchema = true)
@TypeConverters(TagConverters::class)
abstract class DineTrackDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao

    companion object {
        @Volatile private var instance: DineTrackDatabase? = null

        fun getInstance(context: Context): DineTrackDatabase =
            instance ?: synchronized(this) {
                instance ?: build(context.applicationContext).also { instance = it }
            }

        // onCreate runs only for a new database, so deleting every row never reseeds it.
        fun build(context: Context, name: String = "dinetrack.db"): DineTrackDatabase =
            Room.databaseBuilder(context, DineTrackDatabase::class.java, name)
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val converter = TagConverters()
                        MockRestaurantData.restaurants.forEach { item ->
                            db.execSQL(
                                "INSERT INTO restaurants (id, name, address, phone, description, tags, rating) VALUES (?, ?, ?, ?, ?, ?, ?)",
                                arrayOf<Any>(item.id, item.name, item.address, item.phone,
                                    item.description, converter.encode(item.tags), item.rating)
                            )
                        }
                    }
                }).build()
    }
}
