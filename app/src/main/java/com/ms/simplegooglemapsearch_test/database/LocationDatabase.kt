package com.ms.simplegooglemapsearch_test.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RecentResultEntity::class), version = 1)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun getDao(): RecentLocationDao

    companion object {
        private var INSTANCE: LocationDatabase? = null

        fun getInstance(context: Context): LocationDatabase? {
            if (INSTANCE == null) {
                synchronized(LocationDatabase::class.java) {
                    INSTANCE =
                        Room.databaseBuilder(context, LocationDatabase::class.java, "location-db").build()
                }
            }
            return INSTANCE
        }
    }
}