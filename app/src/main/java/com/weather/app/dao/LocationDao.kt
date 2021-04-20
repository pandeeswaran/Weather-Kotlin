package com.weather.app.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.weather.app.data.entity.LocationEntity

@Dao
interface LocationDao {

    @Query("SELECT * FROM LocationEntity")
    fun getAll(): List<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(locationEntity: LocationEntity)

    @Query("SELECT * FROM LocationEntity WHERE id=:id ")
    fun loadSingleEntity(id: Int): LocationEntity

    @Query("DELETE FROM LocationEntity")
    fun deleteAll()

    @Delete
    fun deleteByObject(entity: LocationEntity)

    @Query("SELECT * FROM LocationEntity WHERE id=:id ")
    fun deleteSingleEntity(id: Int): LiveData<LocationEntity>

}