package com.technowalker.trackfuel.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.technowalker.trackfuel.model.Ride
import com.technowalker.trackfuel.service.RideDatabase
import kotlinx.coroutines.launch

class RideViewModel(application: Application) : BaseViewModel(application) {
    val rideList = MutableLiveData<List<Ride>>()
    val rideError = MutableLiveData<Boolean>()
    val rideLoading = MutableLiveData<Boolean>()

    fun refreshData() {

        val mRideList = arrayListOf<Ride>()

        getAllRidesFromRoom(mRideList)

    }

    private fun showRideList(mRideList: List<Ride>) {

        rideList.value = mRideList
        rideError.value = false
        rideLoading.value = false
    }

    private fun getRidesFromDatabase(list: List<Ride>) {
        launch {
            val dao = RideDatabase(getApplication()).rideDao()
            dao.getAllRides()

        }
        showRideList(list)

    }
    private fun getAllRidesFromRoom(list: List<Ride>) {
        launch {
            val dao =RideDatabase(getApplication()).rideDao()
            val rides = dao.getAllRides()
            showRideList(rides) }
    }


}