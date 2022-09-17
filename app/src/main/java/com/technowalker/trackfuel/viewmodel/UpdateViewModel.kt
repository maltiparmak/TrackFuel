package com.technowalker.trackfuel.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.technowalker.trackfuel.model.Ride
import com.technowalker.trackfuel.service.RideDatabase
import com.technowalker.trackfuel.service.RideRepo
import kotlinx.coroutines.launch

class UpdateViewModel(application: Application) : BaseViewModel(application) {

    val rideLiveData = MutableLiveData<Ride>()


     val repo: RideRepo

    init {
        val rideDao = RideDatabase(getApplication()).rideDao()
        repo = RideRepo(rideDao)

    }


    fun refreshData(ride: Ride) {
        getRideWithUuid(ride)


    }


    fun updateMyRide(mRide: Ride) {

        launch {
            repo.updateRide(mRide)
        }
    }

    fun deleteMyRide(mRide: Ride) {
        launch {
            repo.deleteRide(mRide)
        }
    }

    private fun getRideWithUuid(ride: Ride) {

        launch {

            rideLiveData.value = ride

        }


    }

}