package com.guidomia.notcompose

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guidomia.notcompose.model.CarModel
import com.guidomia.notcompose.util.getJsonDataFromAsset


class MainViewModel: ViewModel(){

    private val _carList = MutableLiveData<List<CarModel>>()
    val carList: LiveData<List<CarModel>> get() = _carList

    fun loadCarsFromJsonFile(context: Context, fileName: String) {
        val cars = getCarList(context, fileName)
        _carList.value = cars
    }

    private fun getCarList(context: Context, fileName: String): List<CarModel> {
        val jsonFileString = getJsonDataFromAsset(context, fileName)
        val type = object : TypeToken<List<CarModel>>() {}.type
        return Gson().fromJson(jsonFileString, type)
    }
}