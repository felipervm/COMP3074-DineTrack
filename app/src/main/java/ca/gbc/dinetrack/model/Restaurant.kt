package ca.gbc.dinetrack.model

data class Restaurant(val id: Long, val name: String, val address: String, val phone: String, val description: String, val tags: List<String>, val rating: Int)
