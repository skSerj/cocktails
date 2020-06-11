package com.sourceit.ocktails.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CocktailsEntity (
    @PrimaryKey
    val id: Long,
    val name: String,
    val strImageURL: String,
    val idDrinkOnServer: String
)