package com.guidomia.notcompose.util


import android.content.Context
import java.io.IOException
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale


val format = DecimalFormat("#,###")

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


