package ca.gbc.dinetrack.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class Restaurant(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val name: String,

    val address: String,

    val phone: String,

    val description: String,

    val tags: List<String>,

    val rating: Int
)