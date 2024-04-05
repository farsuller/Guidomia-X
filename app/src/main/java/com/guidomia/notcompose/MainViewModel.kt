package com.guidomia.notcompose

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guidomia.notcompose.model.CarModel
import com.guidomia.notcompose.util.getJsonDataFromAsset


class MainViewModel: ViewModel(){

    fun getCarList(context: Context): List<CarModel> {
        val jsonFileString = getJsonDataFromAsset(context, "car_list.json")
        val type = object : TypeToken<List<CarModel>>() {}.type
        return Gson().fromJson(jsonFileString, type)
    }
}