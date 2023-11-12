package com.example.tourappparte2.domain
import com.example.tourappparte2.data.TourPackage
import com.example.tourappparte2.repositories.PackageRepository

class PackageService {
    fun getPackageList(): List<TourPackage> = PackageRepository.get()

    fun getPackageById(id: Long): TourPackage = PackageRepository.getById(id)
}