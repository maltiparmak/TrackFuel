package com.technowalker.trackfuel.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity
data class Ride(

    @ColumnInfo(name = "rideDistance")
    val rideDistance: Double?,
    @ColumnInfo(name = "rideLiter")
    val rideLiter: Double?,
    @ColumnInfo(name = "rideMoney")
    val rideMoney: Double?,
    @PrimaryKey(autoGenerate = true)
    val uuid: Int,
    @ColumnInfo(name = "dateAdded")
    val dateAdded: Date,
    @ColumnInfo(name="rideDaySayi")
    val rideDaySayi: Int,
    @ColumnInfo(name="rideYearSayi")
    val rideYearSayi: Int,

    @ColumnInfo(name="rideDay")
    val rideDay: String,
    @ColumnInfo(name="rideMonth")
    val rideMonth: String,
    @ColumnInfo (name="rideNote")
    val rideNote: String




): Parcelable

