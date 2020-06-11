package com.sourceit.ocktails.network.model

data class CocktailDetails(
    val drinks: List<DrinkDetails>
)

class DrinkDetails {
    var strDrink: String? = null
    var strAlcoholic: String? = null
    var strGlass: String? = null
    var strInstructions: String? = null
    var strDrinkThumb: String? = null
}