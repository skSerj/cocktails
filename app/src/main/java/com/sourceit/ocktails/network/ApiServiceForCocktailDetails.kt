package com.sourceit.ocktails.network

import com.sourceit.ocktails.network.model.CocktailDetails
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

object ApiServiceForCocktailDetails {
    private const val END_POINT = "https://www.thecocktaildb.com/api/json/v1/1/"
    private val cocktailDetailsApi: CocktailDetailsApi

    fun getData(cocktailId: String?) = cocktailDetailsApi.getDetails(cocktailId)

    interface CocktailDetailsApi {
        @GET("lookup.php")
        fun getDetails(
            @Query("i") cocktailId: String?
        ): Observable<CocktailDetails>
    }

    init {
        val interceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(END_POINT)
            .client(client)
            .build()
        cocktailDetailsApi = retrofit.create(CocktailDetailsApi::class.java)
    }
}
