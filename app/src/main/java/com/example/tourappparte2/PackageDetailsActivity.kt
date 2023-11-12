package com.example.tourappparte2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tourappparte2.data.Purchase
import com.example.tourappparte2.data.TourPackage
import com.example.tourappparte2.data.User
import com.example.tourappparte2.databinding.ActivityPackageDetailsBinding
import com.example.tourappparte2.domain.TourApp
import com.example.tourappparte2.repositories.PurchaseRepository
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PackageDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPackageDetailsBinding
    private var currentPackage: TourPackage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPackageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val packageId = intent.getLongExtra("PACKAGE_ID", 0)
        currentPackage = TourApp.packageService.getPackageById(packageId)

        val actualUser = intent.getSerializableExtra("ACTUAL_USER") as User
        val commission = TourApp.calculateComissionByTransport(currentPackage?.price!!, currentPackage?.transport!!)
        val finalPrice = currentPackage?.price?.plus(commission)

        //Comprueba si el dinero es suficiente, si es asi genera una Purchase y la agrega al inicio de la lista
        fun addNewPurchase(): Boolean {
            var sePudoComprar = false;
            if(actualUser.money >= finalPrice!!) {
                sePudoComprar = true;
                val id = PurchaseRepository.get().last().id + 1
                val userId = actualUser.id
                val packageId = currentPackage?.id
                val amount = finalPrice
                val createdDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                val newPurchase = Purchase(id, userId, packageId!!, amount, createdDate)
                TourApp.purchaseService.purchasePackage(newPurchase)
            }
            return  sePudoComprar
        }

        Picasso.get()
            .load(currentPackage?.logo)
            .into(binding.tvDetailsImage)
        binding.tvDetailsName.text = currentPackage?.destination?.name
        binding.tvDetailsPackageName.text = currentPackage?.name
        binding.rbDetailsRatingBar.rating = currentPackage?.stars?.toFloat()!!
        binding.tvDetailsDays.text = "${currentPackage?.duration} días"
        binding.tvDetailsDescription.text = currentPackage?.destination?.description
        binding.tvTotalPrice.text = "Precio final: $finalPrice$"
        binding.btnDetailsBack.setOnClickListener{
            onBackPressed()
        }
        //Abre un dialogFragment, se le pasa el condicional, nombre de paquete y usuario
        binding.btnComprarPackage.setOnClickListener{
            val dialogFragment = PurchaseDialogFragment(addNewPurchase(), currentPackage?.name!!, actualUser)
            dialogFragment.show(supportFragmentManager, "PurchaseDialogFragment")
        }
    }


}