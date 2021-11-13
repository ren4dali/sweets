package com.example.cupcake.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cupcake.R
import com.example.cupcake.model.Sweets

class SweetsListAdapter(
    private val context: Context, dataSet: List<Sweets>
) : RecyclerView.Adapter<SweetsListAdapter.ItemViewHolder>() {

    private val sweets = dataSet

    //here we take from list_product.xml
    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val imageId: ImageView = view.findViewById(R.id.productImage)
        val priceId: TextView = view.findViewById(R.id.productPrice)
        val nameId: TextView = view.findViewById(R.id.productName)
        val button: Button = view.findViewById<Button>(R.id.buy)


    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SweetsListAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.sweets_list, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: SweetsListAdapter.ItemViewHolder, position: Int) {

        val sweet = sweets[position]
        holder.imageId.setImageResource(sweet.sweetImage .toInt())
        val resources = context.resources
        holder.nameId.text= sweet.sweetName
        holder.priceId.text= resources.getString(R.string.product_price, sweet.price)
    }

    override fun getItemCount(): Int = sweets.size

}