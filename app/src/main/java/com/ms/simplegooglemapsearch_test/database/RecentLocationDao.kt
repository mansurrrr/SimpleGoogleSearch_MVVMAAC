package com.ms.simplegooglemapsearch_test.database

import androidx.room.*
import io.reactivex.Observable

@Dao
interface RecentLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(input: RecentResultEntity)

    @Delete
    fun deleteLocation(input: RecentResultEntity)

    @Query("SELECT * FROM recentresultentity")
    fun getLocations(): Observable<List<RecentResultEntity>>?

    @Query("DELETE FROM recentresultentity")
    fun removeAll()
}