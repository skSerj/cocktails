package com.sourceit.ocktails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sourceit.ocktails.network.model.Drink
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity(), ActivityNavigation {
    private lateinit var disposable: Disposable
    private val listOfDrinks: MutableList<Drink> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, CocktailsFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun showFragmentWithCocktailDetails(drink: String) {
        Log.d("fragmentNavigator", "FragmentNavigator OK")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, CocktailDetailsFragment.newInstance(drink))
            .addToBackStack(null)
            .commit()
    }
}