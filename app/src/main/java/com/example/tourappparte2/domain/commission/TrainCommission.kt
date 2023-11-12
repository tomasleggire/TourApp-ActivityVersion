package com.example.tourappparte2.domain.commission
import java.time.DayOfWeek
import java.time.LocalDate

class TrainCommission(private val actualDate: LocalDate) : TransportCommission {
    val COMISSION_SATURDAY = 0.03
    val COMISSION_SUNDAY = 0.0075
    override fun calculateComission(price: Double): Double {
        return if (actualDate.dayOfWeek == DayOfWeek.SATURDAY || actualDate.dayOfWeek == DayOfWeek.SUNDAY) {
            price.times(COMISSION_SATURDAY)
        } else {
            price.times(COMISSION_SUNDAY)
        }
    }
}