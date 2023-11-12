package com.example.tourappparte2.data

data class Purchase(
    val id: Long,
    val userId: Long,
    var packageId: Long,
    val amount: Double,
    val createdDate: String,
)
