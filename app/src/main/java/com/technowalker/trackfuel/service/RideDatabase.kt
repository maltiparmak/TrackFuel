package com.technowalker.trackfuel.service

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.technowalker.trackfuel.model.Ride


@Database(entities = arrayOf(Ride::class),   version = 6 )
@TypeConverters(DateConverters::class)
abstract class RideDatabase: RoomDatabase() {

    abstract fun rideDao (): RideDao

    companion object {

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Ride "
                        + " ADD COLUMN dateAdded INTEGER DEFAULT 1 NOT NULL")
            }
        }
        val MIGRATION_2_3 = object :Migration(2,3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Ride "
                        + " ADD COLUMN rideDaySayi INTEGER DEFAULT 1 NOT NULL")
                database.execSQL("ALTER TABLE Ride "
                        + " ADD COLUMN rideYearSayi INTEGER DEFAULT 1 NOT NULL")


            }
        }
        val MIGRATION_3_4 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Ride "
                        + " ADD COLUMN rideMonth TEXT DEFAULT '' NOT NULL")
                database.execSQL("ALTER TABLE Ride "
                        + " ADD COLUMN rideDay TEXT DEFAULT '' NOT NULL ")

            }
        }
        val MIGRATION_4_5 = object :Migration(4,5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Ride "
                        + " ADD COLUMN rideDistance REAL DEFAULT 1.0 NOT NULL")
                database.execSQL("ALTER TABLE Ride "
                        + " ADD COLUMN rideLiter REAL DEFAULT 1.0 NOT NULL")
                database.execSQL("ALTER TABLE Ride "
                        + " ADD COLUMN rideMoney REAL DEFAULT 1.0 NOT NULL")


            }
        }
        val MIGRATION_5_6 = object : Migration(5,6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                 database.execSQL("ALTER TABLE Ride "
                        + " ADD COLUMN rideNote TEXT DEFAULT '' NOT NULL")
            }
        }





        @Volatile private var instance: RideDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {


            instance ?: makeDatabase(context).also {
                instance=it
            }

        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,RideDatabase::class.java,"ridedatabase"
        ).addMigrations(MIGRATION_1_2,MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6)
            .fallbackToDestructiveMigration()
            .build()


        }
    }
