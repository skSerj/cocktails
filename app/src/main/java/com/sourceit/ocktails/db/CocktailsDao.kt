package com.sourceit.ocktails.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sourceit.ocktails.network.model.Drink
import io.reactivex.Flowable

@Dao
interface CocktailsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cocktails: List<Drink>)

    @Query("SELECT * FROM CocktailsEntity")
    fun selectAll(): Flowable<List<CocktailsEntity>>
}