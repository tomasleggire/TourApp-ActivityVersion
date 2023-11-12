package com.example.tourappparte2.domain
import com.example.tourappparte2.data.Purchase
import com.example.tourappparte2.repositories.PurchaseRepository

class PurchaseService {
    fun purchasePackage(purchase: Purchase) = PurchaseRepository.add(purchase)

    fun getUserPurchaseList(userId: Long): List<Purchase> = PurchaseRepository.getByUserId(userId)
}