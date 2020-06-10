package com.sourceit.ocktails.network

import com.sourceit.ocktails.network.model.Cocktails
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiServiceForListOfCocktails {
    private const val END_POINT = "https://www.thecocktaildb.com/api/json/v1/1/"
    private val cocktailsApi: CocktailsApi

    val data: Observable<Cocktails>
        get() = cocktailsApi.allCocktails

    interface CocktailsApi {
        @get:GET("filter.php?a=Alcoholic")
        val allCocktails: Observable<Cocktails>
    }

    init {
        val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(END_POINT)
            .client(client)
            .build()
        cocktailsApi = retrofit.create(CocktailsApi::class.java)
    }
}