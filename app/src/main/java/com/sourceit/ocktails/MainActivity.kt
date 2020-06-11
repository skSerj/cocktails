package com.sourceit.ocktails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sourceit.ocktails.fragments.CocktailDetailsFragment
import com.sourceit.ocktails.fragments.CocktailsFragment
import com.sourceit.ocktails.interfaces.ActivityNavigation
import com.sourceit.ocktails.network.model.Drink
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity(),
    ActivityNavigation {

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