package ca.gbc.dinetrack.data

import androidx.room.TypeConverter
import org.json.JSONArray

class TagConverters {
    @TypeConverter
    fun encode(tags: List<String>): String = JSONArray(tags).toString()

    @TypeConverter
    fun decode(value: String): List<String> {
        val array = JSONArray(value)
        return List(array.length()) { array.getString(it) }
    }
}
