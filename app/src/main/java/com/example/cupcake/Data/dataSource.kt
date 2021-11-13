package com.example.cupcake.Data

import com.example.cupcake.R
import com.example.cupcake.model.Sweets

object dataSource {

    val dataSet: List<Sweets> = listOf(
        Sweets(
            R.drawable.cupcake.toString(), "Cupcake",
            "2"
        ), Sweets(
            R.drawable.ice_cream.toString(), "Ice Cream",
            "2"
        )
        , Sweets( R.drawable.cookie.toString() ,"Cookie",
            "5"  )
        , Sweets( R.drawable.macaroon.toString() ,"Macaroon",
            "10"  )
        , Sweets( R.drawable.donut_shop .toString() ,"Donut",
            "5"  ))

}