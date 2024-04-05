package com.guidomia.notcompose.model

data class CarModel(
    val consList: List<String>,
    val customerPrice: Double,
    val make: String,
    val marketPrice: Double,
    val model: String,
    val prosList: List<Any>,
    val rating: Int
)