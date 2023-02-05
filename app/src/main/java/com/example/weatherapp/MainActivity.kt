package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.data.responce.WeatherAPIService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val APIService=WeatherAPIService()

        GlobalScope.launch(Dispatchers.Main){
            val currentWeatherResponse=APIService.getCurrentWeatherAsync("New%20York").await()
            temp.text=currentWeatherResponse.current.toString()
        }



    }





}
