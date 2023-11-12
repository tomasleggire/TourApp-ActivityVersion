package com.example.tourappparte2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourappparte2.adapters.PurchaseAdapter
import com.example.tourappparte2.databinding.ActivityPurchasesBinding
import com.example.tourappparte2.domain.TourApp
import com.example.tourappparte2.repositories.UserRepository

class PurchasesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchasesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchasesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getLongExtra("USER_ID", 0)
        val currentUser = UserRepository.findUserById(userId)

        binding.btnBackPurchases.setOnClickListener{
           onBackPressed()
        }

        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvPurchases.layoutManager = linearLayoutManager

        val adapter = currentUser?.let { PurchaseAdapter(this, it) }
        binding.rvPurchases.adapter = adapter

    }
}