package com.guidomia.notcompose.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guidomia.notcompose.R
import com.guidomia.notcompose.model.CarModel
import com.guidomia.notcompose.model.Cars
import com.guidomia.notcompose.util.format


class CarAdapter(private var carList: List<CarModel>) : RecyclerView.Adapter<CarViewHolder>() {

    private val cars = Cars.entries.toTypedArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.car_item_layout, parent, false)
        return CarViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val carItem = carList[position]

        holder.carImage.setImageResource(cars.find { it.model == carItem.model || it.make == carItem.make }!!.imagePath)
        holder.carMake.text = carItem.make

        val starAdapter = StarAdapter(carItem.rating)
        holder.carRate.adapter = starAdapter
        holder.carRate.layoutManager = LinearLayoutManager(holder.itemView.context,LinearLayoutManager.HORIZONTAL, false)

        val formattedPrice = format.format(carItem.marketPrice / 1000)
        val priceString = holder.itemView.context.getString(R.string.price_placeholder, formattedPrice)
        holder.carPrice.text = priceString

        if (carItem.prosList.isEmpty())
            holder.prosLabel.visibility = View.GONE

        if (carItem.consList.isEmpty())
            holder.consLabel.visibility = View.GONE

        val prosAdapter = ProsConsAdapter(carItem.prosList.filter { it.toString().isNotBlank()})
        holder.prosRecyclerView.adapter = prosAdapter
        holder.prosRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)

        val consAdapter = ProsConsAdapter(carItem.consList.filter { it.isNotBlank()})
        holder.consRecyclerView.adapter = consAdapter
        holder.consRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
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
    val carRate: RecyclerView = itemView.findViewById(R.id.starRecyclerView)

    val prosRecyclerView: RecyclerView = itemView.findViewById(R.id.prosRecyclerView)
    val consRecyclerView: RecyclerView = itemView.findViewById(R.id.consRecyclerView)

    private val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
    private val detailsLayout: LinearLayout = itemView.findViewById(R.id.detailsLayout)
    val prosLabel: TextView = itemView.findViewById(R.id.prosLabel)
    val consLabel: TextView = itemView.findViewById(R.id.consLabel)

    init {
        constraintLayout.setOnClickListener {
            if (detailsLayout.visibility == View.VISIBLE) {
                detailsLayout.visibility = View.GONE
            } else {
                detailsLayout.visibility = View.VISIBLE
            }
        }
        carRate.visibility = View.VISIBLE
    }
}

private class CarDiffCallback(private val oldList: List<CarModel>, private val newList: List<CarModel>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}