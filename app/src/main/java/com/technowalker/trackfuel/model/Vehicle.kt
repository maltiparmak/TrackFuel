package com.technowalker.trackfuel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vehicle(
    @ColumnInfo( name = "vehiclePlate")
    val vehiclePlate: String?,
    @ColumnInfo( name = "vehicleGasType")
    val vehicleGasType: String?,
    @ColumnInfo( name = "vehicleName")
    val vehicleName: String?,
    @ColumnInfo( name = "vehicleBrand")
    val vehicleBrand: String?,
    @ColumnInfo( name = "vehicleModel")
    val vehicleModel: String?
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid: Int =0
}
