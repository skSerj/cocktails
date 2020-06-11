package com.sourceit.ocktails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sourceit.ocktails.network.ApiServiceForCocktailDetails
import com.sourceit.ocktails.network.model.CocktailDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_coctail_details.*

class CocktailDetailsFragment(idDrink: String) : Fragment() {

    private lateinit var disposable: Disposable
   private val drinkId = idDrink

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coctail_details, container, false)
    }

    companion object {
        fun newInstance(idDrink: String) = CocktailDetailsFragment(idDrink)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("ID", "id is $drinkId ")
        disposable = ApiServiceForCocktailDetails.getData(drinkId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("MyApp", "you in subscribe")
                detail_name.text = it.drinks[0].strDrink
                detail_alcoholic.text = it.drinks[0].strAlcoholic
                detail_glass.text = it.drinks[0].strGlass
                detail_instruction.text= it.drinks[0].strInstructions
                Glide.with(view.context)
                    .load(it.drinks[0].strDrinkThumb)
                    .into(detail_image)
            }, {
                it.printStackTrace()
            })
    }
}
