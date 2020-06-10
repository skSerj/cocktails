package com.sourceit.ocktails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sourceit.ocktails.network.model.Drink
import kotlinx.android.synthetic.main.item_cocktails.view.*

class CocktailsAdapter() :
    RecyclerView.Adapter<CocktailsAdapter.CocktailsHolder>() {
    private val listOfCocktails: MutableList<Drink> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_cocktails, parent, false)
        return CocktailsHolder(v)
    }

    override fun getItemCount() = listOfCocktails.size

    override fun onBindViewHolder(holder: CocktailsHolder, position: Int) {
        val cocktails: Drink = listOfCocktails[position]
        holder.cocktailName.text = cocktails.strDrink
        Glide.with(holder.itemView.context)
            .load(cocktails.strDrinkThumb.toString())
            .into(holder.image)

//        GlideToVectorYou
//            .init()
//            .with(holder.image.context)
//            .load(Uri.parse(cocktails.strDrinkThumb),holder.image)

    }

    class CocktailsHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.img_cocktail
        val cocktailName: TextView = itemView.txt_cocktail_name
    }

    fun update(listOfDrinks: MutableList<Drink>) {
        listOfCocktails.apply {
            clear()
            addAll(listOfDrinks)
        }
        notifyDataSetChanged()
    }
}