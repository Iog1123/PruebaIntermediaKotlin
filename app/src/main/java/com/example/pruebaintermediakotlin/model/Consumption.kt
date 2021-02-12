package com.example.pruebaintermediakotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_consumption")
data class Consumption(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val item:String,
    val itemPrice :Int,
    val queantity: Int,
    val total:Int)
