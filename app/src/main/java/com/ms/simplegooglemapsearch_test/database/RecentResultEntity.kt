package com.ms.simplegooglemapsearch_test.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ms.simplegooglemapsearch_test.model.BaseSearchModel

@Entity
class RecentResultEntity: BaseSearchModel() {
    @PrimaryKey(autoGenerate = true)
    val locationId: String = ""
    val lat: String = ""
    val lon: String = ""
}