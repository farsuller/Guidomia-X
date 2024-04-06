package com.guidomia.notcompose.model

import androidx.annotation.DrawableRes
import com.guidomia.notcompose.R


enum class Cars(
    @DrawableRes val imagePath: Int = 0,
    val make: String = "",
    val model: String = ""
) {
    Alpine(
        imagePath = R.drawable.alphine,
        make = "Alpine",
        model = "Roadster"
    ),

    BMW(
        imagePath = R.drawable.bmw,
        make = "BMW",
        model = "3300i"
    ),
    MercedesBenz(
        imagePath = R.drawable.mercedez,
        make = "Mercedes Benz",
        model = "GLE coupe"
    ),
    LandRover(
        imagePath = R.drawable.range_rover,
        make = "Land Rover",
        model = "Range Rover"
    ),

    Tacoma(
        imagePath = R.drawable.tacoma,
        make = "Tacoma",
    ),

}