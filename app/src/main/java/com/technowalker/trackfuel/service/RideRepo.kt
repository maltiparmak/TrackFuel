package com.technowalker.trackfuel.service


import com.technowalker.trackfuel.model.Ride

class RideRepo( private val rideDao: RideDao) {

    suspend fun addRide (ride: Ride) {
        rideDao.insertRide(ride)
    }
    suspend fun updateRide (ride: Ride) {
        rideDao.updateRide(ride)
    }
    suspend fun deleteRide (ride: Ride) {
        rideDao.deleteRide(ride)
    }





}