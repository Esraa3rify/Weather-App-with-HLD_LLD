package com.example.weatherapp.data.responce

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val YOUR_ACCESS_KEY ="bc3be08834d7fafb385638a8fae9fe70"


//const val API_KEY ="89e8bd89085b41b7a4b142029180210"

//http://api.weatherstack.com/current?access_key=bc3be08834d7fafb385638a8fae9fe70&query=New%20York


interface WeatherAPIService{



    @GET("current")
    fun getCurrentWeatherAsync(
        @Query("query") location: String


        ):Deferred<CurrentWeatherResponse>


    companion object {
        operator fun invoke(
        ): WeatherAPIService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", YOUR_ACCESS_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create( WeatherAPIService::class.java)
        }
    }
}