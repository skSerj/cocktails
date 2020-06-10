package com.sourceit.ocktails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sourceit.ocktails.network.ApiServiceForCocktailDetails
import com.sourceit.ocktails.network.model.CocktailDetails
import com.sourceit.ocktails.network.model.Drink
import com.sourceit.ocktails.network.model.DrinkDetails
import kotlinx.android.synthetic.main.fragment_coctail_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_COCKTAIL_ID = "cocktailId"

class CocktailDetailsFragment : Fragment() {

    private var cocktailId: String? = null
    private val listOfDetails: MutableList<DrinkDetails> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cocktailId = it.getString(ARG_COCKTAIL_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coctail_details, container, false)
    }

    companion object {
        fun newInstance(idDrink: String) =
            CocktailDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_COCKTAIL_ID, cocktailId)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ApiServiceForCocktailDetails.getData(cocktailId)
            .enqueue(object : Callback<CocktailDetails> {
                override fun onResponse(
                    call: Call<CocktailDetails>,
                    response: Response<CocktailDetails>
                ) {
                    listOfDetails.addAll(response.body()?.cocktailDetailsList!!)
                    detail_name.text = listOfDetails[0].strDrink
                    detail_alcoholic.text = listOfDetails[0].strAlcoholic
                    detail_glass.text = listOfDetails[0].strGlass
                    detail_instruction.text = listOfDetails[0].strInstructions
                    Glide.with(view.context)
                        .load(listOfDetails[0].strDrinkThumb)
                        .into(detail_image)
                }

                override fun onFailure(call: Call<CocktailDetails>, t: Throwable) {
                    Toast.makeText(view.context, "something went wrong", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            })
    }
}
