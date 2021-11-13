package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {

    val dateOptions = getPickupOptions()
    private var _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> get() = _quantity

    private var _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> get() = _flavor

    private var _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    private var _price = MutableLiveData<Double>()

    // using Transformations.map to initialize the var to make it with local currency format
    val price: LiveData<String>
        get() = Transformations.map(_price) {
            NumberFormat.getCurrencyInstance().format(it)
        }


    init {
        restOrder()
    }


    //fun to reset the order
     fun restOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _price.value = 0.0
        _date.value = dateOptions[0]
    }


    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()

    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }

    //return true if the flavor is null or empty
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()

    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // Create list of dates starting with the current day and the following 3 days
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }


    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        // If the user selected today for pickup, add the surcharge
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }
}
