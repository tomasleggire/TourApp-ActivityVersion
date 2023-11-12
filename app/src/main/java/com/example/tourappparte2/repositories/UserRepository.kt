package com.example.tourappparte2.repositories
import com.example.tourappparte2.data.User

object UserRepository {

    private val users = mutableListOf<User>()

    init {
        users.add(User(1504L, "BRIAN_BAYARRI", "abc123", "Brian", "Bayarri", 3500000.50, "2022/12/10"))
        users.add(User(2802L, "AHOZ", "lock_password", "Aylen", "Hoz", 200000.50, "2021/01/11"))
        users.add(User(1510L, "Diegote", "@12345", "Diego", "Gonzalez", 120000.0, "2018/04/15"))
    }

    fun login(nickName: String, password: String): User? {
        return users.find { it.nickName == nickName && it.password == password }
    }

    fun findUserById(id: Long): User? {
        return users.find { it.id == id }
    }
}