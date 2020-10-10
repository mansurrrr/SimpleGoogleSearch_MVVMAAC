package com.ms.simplegooglemapsearch_test.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ms.simplegooglemapsearch_test.model.BaseSearchModel

@Entity
data class RecentResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val locationId: String = "",
    val lat: String = "",
    val lon: String = ""
) : BaseSearchModel()