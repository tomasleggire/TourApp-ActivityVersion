package com.example.tourappparte2.domain.commission
import java.time.LocalTime

class AirplaneCommission(private val currentHour: LocalTime) : TransportCommission {
    val COMISSION_1= 0.01
    val COMISSION_2 = 0.03
    val COMISSION_HOUR_1 = 15
    val COMISSION_MINUTE_1 = 0
    val COMISSION_HOUR_2 = 22
    val COMISSION_MINUTE2 =  30
    override fun calculateComission(price: Double): Double {
        return if (currentHour.isAfter(LocalTime.of(COMISSION_HOUR_1, COMISSION_MINUTE_1)) && currentHour.isBefore(LocalTime.of(COMISSION_HOUR_2, COMISSION_MINUTE2))) {
            price.times(COMISSION_1)
        } else {
            price.times(COMISSION_2)
        }
    }
}