package com.example.tourappparte2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tourappparte2.data.User
import com.example.tourappparte2.databinding.PurchaseItemViewBinding
import com.example.tourappparte2.domain.TourApp
import com.squareup.picasso.Picasso

class PurchaseAdapter(private val context: Context, private val actualUser: User): RecyclerView.Adapter<PurchaseAdapter.ViewHolder>() {

    val purchasesList = TourApp.purchaseService.getUserPurchaseList(actualUser.id)
    class ViewHolder(val binding: PurchaseItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PurchaseItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PurchaseAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return purchasesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val purchase = purchasesList[position]
        val packagePurchase = TourApp.packageService.getPackageById(purchase.packageId)

        Picasso.get()
            .load(packagePurchase.logo)
            .into(holder.binding.ivImagePurchaseItem)

        holder.binding.tvTitlePurchaseItem.text = packagePurchase.name
        holder.binding.tvDatePurchase.text = purchase.createdDate
        holder.binding.tvPricePurchase.text = "$ ${purchase.amount}ARS"

    }
}