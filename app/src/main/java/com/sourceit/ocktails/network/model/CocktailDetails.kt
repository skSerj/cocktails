package com.sourceit.ocktails.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CocktailDetails(
    @SerializedName("drinks")
    @Expose
    val cocktailDetailsList: List<DrinkDetails>
)

class DrinkDetails {
    var strDrink: String? = null
    var strAlcoholic: String? = null
    var strGlass: String? = null
    var strInstructions: String? = null
    var strDrinkThumb: String? = null
}