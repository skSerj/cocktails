package com.sourceit.ocktails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CocktailDetailsFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coctail_details, container, false)
    }

    companion object {
        fun newInstance() = CocktailDetailsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}