package com.weather.app.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.weather.app.dao.LocationDao
import com.weather.app.data.entity.LocationEntity

@Database(entities = [LocationEntity::class], version = 1, exportSchema = false)
public abstract class AppDatabase : RoomDatabase() {

    abstract fun locationEntity(): LocationDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "weather"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}