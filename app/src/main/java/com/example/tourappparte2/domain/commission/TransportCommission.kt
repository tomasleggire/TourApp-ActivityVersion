package com.example.tourappparte2.domain.commission

interface TransportCommission {
    fun calculateComission(price: Double): Double
}