package com.example.pruebaintermediakotlin.model

import androidx.lifecycle.LiveData

class ConRepository(private val consumptionDao: ConsumptionDao) {

    val lisAllConsumption: LiveData<List<Consumption>> = consumptionDao.getAllConsumption()

    suspend fun insertConsumption(consumption: Consumption){
        consumptionDao.insertConsumption(consumption)
    }

    suspend fun deleteAllConsumption(){
        consumptionDao.deleteAllConsumption()
    }

    suspend fun deleteConsumption(consumption: Consumption){
        consumptionDao.deletedConsumption(consumption)
    }


}