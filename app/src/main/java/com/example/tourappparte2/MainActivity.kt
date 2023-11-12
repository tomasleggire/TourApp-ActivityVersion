package com.example.tourappparte2
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tourappparte2.data.User
import com.example.tourappparte2.databinding.ActivityMainBinding
import com.example.tourappparte2.domain.TourApp
import com.example.tourappparte2.domain.UserService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Si el usuario existe lo loguea, sino devuelve null y lanza un Toast
        fun tryLoginUser(): User? {
            var userName = binding.tvName.text.toString()
            var password = binding.tvPassword.text.toString()
            return TourApp.userService.login(userName, password)
        }

        //Si el usuario se puede logear, es redirigido hacia el dashboard
        binding.btnLogin.setOnClickListener{
            var userSelected = tryLoginUser()
            if(userSelected !== null) {
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("USER_ID", userSelected.id)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña invalido", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onBackPressed() {
        // No hacer nada
    }
}