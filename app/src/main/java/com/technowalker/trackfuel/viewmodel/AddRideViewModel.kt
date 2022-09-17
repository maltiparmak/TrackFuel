package com.technowalker.trackfuel.viewmodel

import android.app.Application
import com.technowalker.trackfuel.model.Ride
import com.technowalker.trackfuel.service.RideDatabase
import kotlinx.coroutines.launch

class AddRideViewModel (application: Application): BaseViewModel(application) {

     fun addRideToRoomDB (ride: Ride) {
        launch {
            val dao = RideDatabase(getApplication()).rideDao()
            dao.insertRide(ride)   }
    }




    }




