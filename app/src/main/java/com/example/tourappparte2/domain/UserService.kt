package com.example.tourappparte2.domain
import com.example.tourappparte2.data.User
import com.example.tourappparte2.repositories.UserRepository

class UserService {
    private var user: User? = null

    fun login(nickName: String, password: String): User? {
        if (nickName.isNotBlank() && password.isNotBlank()) {
            user = UserRepository.login(nickName, password)
            return user
        }else {
            return null
        }
    }

    fun logOut() {
        user = null
    }
}