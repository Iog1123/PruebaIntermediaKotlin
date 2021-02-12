package com.example.pruebaintermediakotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pruebaintermediakotlin.model.ConDataBase
import com.example.pruebaintermediakotlin.model.ConRepository
import com.example.pruebaintermediakotlin.model.Consumption
import kotlinx.coroutines.launch

class ConViewModel (application: Application): AndroidViewModel(application){


    private val repository: ConRepository

    val allConsumption: LiveData<List<Consumption>>

    init {
        val consumptionDao= ConDataBase.getDataBase(application).getConDao()
        repository=ConRepository(consumptionDao)
        allConsumption=repository.lisAllConsumption

    }

    fun insertConsumption(con: Consumption)= viewModelScope.launch{
        repository.insertConsumption(con)
    }

    fun deleteConsumption(con: Consumption)= viewModelScope.launch {
         repository.deleteConsumption(con)
    }

    fun deleteAllConsumption()= viewModelScope.launch {
        repository.deleteAllConsumption()
    }

    fun getAllConsumtion()= viewModelScope.launch {
        repository.lisAllConsumption
    }

    // lista creada para ser observada

    private var selectedConsumption: MutableLiveData<Consumption> = MutableLiveData()

    fun selected(con:Consumption?){
        selectedConsumption.value= con
    }
    fun selectedItem(): LiveData<Consumption> = selectedConsumption

    override fun onCleared() {
        super.onCleared()
    }
}