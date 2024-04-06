package com.guidomia.notcompose

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.guidomia.notcompose.adapter.CarAdapter
import com.guidomia.notcompose.databinding.ActivityMainBinding
import com.guidomia.notcompose.model.CarModel
import com.guidomia.notcompose.util.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: MainViewModel by viewModels()

    private val carList = mutableListOf<CarModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val adapter = CarAdapter(carList)
        binding.recyclerViewCar.adapter = adapter
        binding.recyclerViewCar.layoutManager = LinearLayoutManager(this)

        viewModel.loadCarsFromJsonFile(this, "car_list.json")

        viewModel.carList.observe(this) { cars ->
            adapter.updateData(cars)
        }

        viewModel.filteredCarList.observe(this) { filteredCars ->
            adapter.updateData(filteredCars)
        }

        binding.filterMake.addTextChangedListener { text ->
            viewModel.filterCars(text.toString(), binding.filterModel.text.toString())
        }

        binding.filterModel.addTextChangedListener { text ->
            viewModel.filterCars(binding.filterMake.text.toString(), text.toString())
        }
    }
}