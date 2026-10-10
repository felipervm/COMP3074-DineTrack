package ca.gbc.dinetrack.data

import ca.gbc.dinetrack.model.Restaurant

object MockRestaurantData {
    val restaurants = listOf(
        Restaurant(1, "Bar Isabel", "797 College St, Toronto", "416-532-2222", "Spanish-inspired restaurant with a warm atmosphere.", listOf("Spanish", "Date Night"), 5),
        Restaurant(2, "Pizzeria Libretto", "155 University Ave, Toronto", "416-551-0433", "Neapolitan pizza restaurant.", listOf("Italian", "Pizza"), 4),
        Restaurant(3, "Pai", "18 Duncan St, Toronto", "416-901-4724", "Popular Northern Thai restaurant.", listOf("Thai", "Favourite"), 5)
    )
}
