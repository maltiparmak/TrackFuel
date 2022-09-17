package com.technowalker.trackfuel.service

import androidx.room.*
import com.technowalker.trackfuel.model.Ride
import com.technowalker.trackfuel.model.Vehicle

@Dao
interface RideDao {

    //Data Access Object

    //sürüş ekleme
    @Insert
    suspend fun insterAll (vararg rides: Ride) : List<Long>

    @Query("SELECT * FROM ride ORDER BY dateAdded DESC")
    fun getAll(): List<Ride>

    // sürüş ekle
    @Insert
    suspend fun insertRide (vararg ride: Ride)

    // tüm sürüşleri çağırma
    @Query("SELECT * FROM ride ORDER BY dateAdded DESC")
    suspend fun getAllRides () : List<Ride>

    // tek bir sürüş çağırma
    @Query ("SELECT * FROM ride WHERE uuid = :rideId")
    suspend fun getRidewUuid (rideId: Int) : Ride

    // güncelleme
    @Update
    suspend fun  updateRide (vararg ride: Ride)

    @Delete
    suspend fun deleteRide (vararg ride: Ride)
/*
    //araç ekle
    @Insert
    suspend fun insertVehicle ( vararg vehicle: Vehicle)

    // güncelleme
    @Update
    suspend fun  updateVehicle (vehicle: Vehicle)

    // tüm araçları çağırma
    @Query("SELECT * FROM vehicle")
    suspend fun getAllVehicle () : List<Vehicle>

    // tek bir araç çağırma
    @Query ("SELECT *FROM ride WHERE uuid = :vehicleId")
    suspend fun getVehicle(vehicleId: Int) : Vehicle

 */



}