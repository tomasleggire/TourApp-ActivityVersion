package com.example.tourappparte2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourappparte2.adapters.DashboardAdapter
import com.example.tourappparte2.databinding.ActivityDashboardBinding
import com.example.tourappparte2.domain.TourApp
import com.example.tourappparte2.repositories.UserRepository

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getLongExtra("USER_ID", 0)
        val actualUser = UserRepository.findUserById(userId)

        if (actualUser != null) {
            binding.tvSaludoNombre.text = actualUser.name
        }

        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvDashboard.layoutManager = linearLayoutManager

        val adapter = actualUser?.let { DashboardAdapter(this, it) }
        binding.rvDashboard.adapter = adapter

        //Boton para ir a mis compras
        binding.btnPurchases.setOnClickListener{
            val intent = Intent(this, PurchasesActivity::class.java)
            intent.putExtra("USER_ID", actualUser?.id)
            startActivity(intent)
        }

        //Boton para cerrar secion
        binding.btnLogOut.setOnClickListener{
            TourApp.userService.logOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    //Que no se pueda ir hacia atras y no se cierre la app
    override fun onBackPressed() {
        // No hacer nada
    }
}