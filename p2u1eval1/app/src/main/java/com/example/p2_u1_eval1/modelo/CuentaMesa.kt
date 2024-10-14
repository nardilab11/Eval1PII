package com.example.p2_u1_eval1.modelo

//Representa la mesa con sus platos pedidos
class CuentaMesa(val mesa : Int) {
    val _items : MutableList<ItemMesa> = mutableListOf<ItemMesa>()
    var aceptaPropina : Boolean = true

    //Agrega un plato creando un objeto de tipo ItemMesa con una cantidad dada
    fun agregarItem(itemMenu : ItemMenu, cantidad : Int) {
        val itemMesa = ItemMesa(itemMenu, cantidad)
        _items.add(itemMesa)
    }

    //Igual que arriba pero agrega solo 1
    fun agregarItem(itemMenu : ItemMenu) {
        val itemMesa = ItemMesa(itemMenu, 1)
        _items.add(itemMesa)
    }

    //Calcula el total que tiene la mesa sin contar propina
    fun calcularTotalSinPropina():Int {
        var total : Int = 0
        for(item in _items){
            total += item.calcularSubtotal()
        }
        return total
    }

    //Calcula la propina (10%) de la cuenta
    fun calcularPropina():Int {
        val total = calcularTotalSinPropina() * 0.1
        return total.toInt()
    }

    //Calcula el total con propina
    fun calcularTotalConPropina():Int {
        return calcularTotalSinPropina() + calcularPropina()
    }
}