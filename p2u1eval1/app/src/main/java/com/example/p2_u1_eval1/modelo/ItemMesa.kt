package com.example.p2_u1_eval1.modelo

//Representa cada plato que se ha pedido en una mesa
class ItemMesa(val itemMenu : ItemMenu, val cantidad : Int) {

    //Calcula el total de cada producto multiplicando su valor por su cantidad
    fun calcularSubtotal() : Int {
        val valorUnit = itemMenu.precio.toInt()
        return valorUnit * cantidad
    }
}