package com.example.tourappparte2.data

import java.io.Serializable

data class User(
    val id: Long,
    val nickName: String,
    val password: String,
    val name: String,
    val surname: String,
    val money: Double,
    val createdDate: String
): Serializable
