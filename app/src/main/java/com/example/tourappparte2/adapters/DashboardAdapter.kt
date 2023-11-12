package com.example.tourappparte2.adapters
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tourappparte2.PackageDetailsActivity
import com.example.tourappparte2.data.User
import com.example.tourappparte2.databinding.ItemViewBinding
import com.example.tourappparte2.domain.TourApp
import com.squareup.picasso.Picasso

class DashboardAdapter(private val context: Context, private val actualUser: User): RecyclerView.Adapter<DashboardAdapter.ViewHolder>()  {

    var paquetes = TourApp.packageService.getPackageList()


    class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return paquetes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paquete = paquetes[position]
        holder.binding.tvNameOfPackage.text = paquete.name
        holder.binding.tvNameOfDestination.text = paquete.destination.name
        holder.binding.tvDays.text = "${paquete.duration} dias"
        holder.binding.tvPrice.text = "${paquete.price}$"
        holder.binding.rtRatingBar.rating = paquete.stars.toFloat()

        Picasso.get()
            .load(paquete.logo)
            .into(holder.binding.imageView)

        //Iniciar una acitivity pasando id de paquete y usuario para llenar los datos
        holder.binding.btnComprar.setOnClickListener{
            val intent = Intent(context, PackageDetailsActivity::class.java)
            intent.putExtra("PACKAGE_ID", paquete.id)
            intent.putExtra("ACTUAL_USER", actualUser)
            context.startActivity(intent)
        }
    }

}