package com.sourceit.ocktails

import android.app.Application
import com.sourceit.ocktails.db.CocktailsDataBase

class App :Application() {

    lateinit var db: CocktailsDataBase

    override fun onCreate() {
        super.onCreate()
        db = CocktailsDataBase.getInstance(this)
    }
}