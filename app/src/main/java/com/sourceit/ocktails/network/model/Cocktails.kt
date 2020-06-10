package com.sourceit.ocktails.network.model

data class Cocktails(
    val drinks: List<Drink>
)

data class Drink(
    val strDrink: String,
    val strDrinkThumb: String,
    val idDrink: String

)