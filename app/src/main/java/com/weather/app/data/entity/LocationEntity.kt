package com.weather.app.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class LocationEntity(
    @ColumnInfo(name = "placeName") @NotNull val placeName: String?,
    @ColumnInfo(name = "latitude") @NotNull val latitude: Double?,
    @ColumnInfo(name = "longitude") @NotNull val longitude: Double?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}