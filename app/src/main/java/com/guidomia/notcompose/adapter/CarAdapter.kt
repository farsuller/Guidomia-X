package com.guidomia.notcompose.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.guidomia.notcompose.R
import com.guidomia.notcompose.model.CarModel
import com.guidomia.notcompose.model.Cars
import com.guidomia.notcompose.util.format


class CarAdapter(private var carList: List<CarModel>) : RecyclerView.Adapter<CarViewHolder>() {

    val cars = Cars.entries.toTypedArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.car_item_layout, parent, false)
        return CarViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val carItem = carList[position]


        holder.carImage.setImageResource(cars.find { it.model == carItem.model || it.make == carItem.make }!!.imagePath)
        holder.carMake.text = carItem.make

        val formattedPrice = format.format(carItem.marketPrice / 1000)
        val priceString = holder.itemView.context.getString(R.string.price_placeholder, formattedPrice)
        holder.carPrice.text = priceString

    }

    override fun getItemCount() = carList.size

    fun updateData(newCarList: List<CarModel>) {
        val diffResult = DiffUtil.calculateDiff(CarDiffCallback(carList, newCarList))
        carList = newCarList
        diffResult.dispatchUpdatesTo(this)
    }
}

class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val carImage: ImageView = itemView.findViewById(R.id.car_image)
    val carMake: TextView = itemView.findViewById(R.id.car_make)
    val carPrice: TextView = itemView.findViewById(R.id.car_price)
}

private class CarDiffCallback(private val oldList: List<CarModel>, private val newList: List<CarModel>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}