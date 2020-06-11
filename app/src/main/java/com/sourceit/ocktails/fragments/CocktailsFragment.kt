package com.sourceit.ocktails.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sourceit.ocktails.R
import com.sourceit.ocktails.adapter.CocktailsAdapter
import com.sourceit.ocktails.interfaces.ActivityNavigation
import com.sourceit.ocktails.interfaces.OnCocktailClickListener
import com.sourceit.ocktails.network.ApiServiceForListOfCocktails
import com.sourceit.ocktails.network.model.Drink
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_cocktails.*
import java.lang.IllegalArgumentException

class CocktailsFragment() : Fragment(),
    OnCocktailClickListener {

    private lateinit var disposable: Disposable
    private val listOfDrinks: MutableList<Drink> = ArrayList()

    companion object {
        val cocktailsAdapter: CocktailsAdapter =
            CocktailsAdapter(newInstance())

        fun newInstance() = CocktailsFragment()
        private var navigation: ActivityNavigation? = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivityNavigation) {
            navigation = context as ActivityNavigation
        } else {
            throw IllegalArgumentException(context::class.java.name + "Error")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cocktails, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cocktails_list.apply {
            adapter =
                cocktailsAdapter
            layoutManager = LinearLayoutManager(this.context)
        }

        disposable = ApiServiceForListOfCocktails.data
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listOfDrinks.addAll(it.drinks)
                showInfo(listOfDrinks)
            }, {
                showError(it)
                it.printStackTrace()
            })
    }

    private fun showInfo(listOfDrinks: MutableList<Drink>) {
        cocktailsAdapter.update(listOfDrinks)
    }

    private fun showError(it: Throwable) {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun onCocktailClick(drink: String) {
        Log.d("Fragment", "interface is OK")
        navigation?.showFragmentWithCocktailDetails(drink)
    }
}