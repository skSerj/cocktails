package com.sourceit.ocktails

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
        holder.bind(listOfCocktails[position])
    }

    class CocktailsHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private var image: ImageView = itemView.img_cocktail
        private var cocktailName: TextView = itemView.txt_cocktail_name
        var root: View = itemView.root

        fun bind(drink: Drink) {
            cocktailName.text = drink.strDrink
            Glide.with(itemView.context)
                .load(drink.strDrinkThumb)
                .into(image)
            root.setOnClickListener {
                Toast.makeText(it.context, drink.idDrink, Toast.LENGTH_SHORT).show()
                Log.d("MyApp", "you click on item")
            }
        }
    }

    fun update(listOfDrinks: MutableList<Drink>) {
        listOfCocktails.apply {
            clear()
            addAll(listOfDrinks)
        }
        notifyDataSetChanged()
    }

}