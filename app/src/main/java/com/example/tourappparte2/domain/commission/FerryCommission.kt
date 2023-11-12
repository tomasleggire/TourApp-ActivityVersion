package com.example.tourappparte2.domain.commission

class FerryCommission : TransportCommission {
    override fun calculateComission(price: Double): Double {
        return price
    }
}