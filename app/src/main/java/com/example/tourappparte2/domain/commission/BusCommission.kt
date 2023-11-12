package com.example.tourappparte2.domain.commission

class BusCommission : TransportCommission {
    val COMISSION_BUS = 0.02
    override fun calculateComission(price: Double): Double {
        return price.times(COMISSION_BUS)
    }
}