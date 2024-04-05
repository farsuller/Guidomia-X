package com.guidomia.notcompose.util


import android.content.Context
import java.io.IOException
import java.text.NumberFormat
import java.util.Locale

val format: NumberFormat = NumberFormat.getNumberInstance(Locale.US)

fun getJsonDataFromAsset(
    context: Context,
    fileName: String
): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    } catch (exp: IOException) {
        exp.printStackTrace()
        return null
    }

    return jsonString
}


