package com.example.pruebaintermediakotlin.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pruebaintermediakotlin.model.Consumption


@Dao
interface ConsumptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsumption(con: Consumption)

    @Delete
    fun deletedConsumption(con: Consumption)

    @Query("select * from table_consumption")
    fun getAllConsumption(): LiveData<List<Consumption>>

    @Query("delete from table_consumption")
    fun deleteAllConsumption()


}