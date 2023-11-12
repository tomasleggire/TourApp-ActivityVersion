package com.example.tourappparte2.domain
import com.example.tourappparte2.data.TransportType
import com.example.tourappparte2.domain.commission.AirplaneCommission
import com.example.tourappparte2.domain.commission.BusCommission
import com.example.tourappparte2.domain.commission.FerryCommission
import com.example.tourappparte2.domain.commission.TrainCommission
import com.example.tourappparte2.domain.commission.TransportCommission
import java.time.LocalDate
import java.time.LocalTime

object TourApp {
    val userService = UserService()
    val packageService = PackageService()
    val purchaseService = PurchaseService()

    fun calculateComissionByTransport(price: Double, transport: TransportType): Double {
        val commissionType: TransportCommission = when (transport) {
            TransportType.BUS -> BusCommission()
            TransportType.AIRPLANE -> AirplaneCommission(LocalTime.now())
            TransportType.TRAIN -> TrainCommission(LocalDate.now())
            TransportType.FERRY -> FerryCommission()
        }
        return commissionType.calculateComission(price)
    }
}