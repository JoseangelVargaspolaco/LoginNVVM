package com.example.loginnvvm.ui.Menu

import com.example.loginnvvm.R

sealed class LateralMenu (
    val icon : Int,
    val title : String,
    val ruta : String

    ){
//    object Opción_init: LateralMenu(R.drawable.init, "Opción_init", "Opción_init")
    object Opción_1: LateralMenu(R.drawable.plus, "Opción_1", "Opción_1")
    object Opción_2: LateralMenu(R.drawable.plus, "Opción_2", "Opción_2")
    object Opción_3: LateralMenu(R.drawable.plus, "Opción_3", "Opción_3")
}