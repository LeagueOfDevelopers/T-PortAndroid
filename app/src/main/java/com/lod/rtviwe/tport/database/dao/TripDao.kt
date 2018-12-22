package com.lod.rtviwe.tport.database.dao

import androidx.room.*
import com.lod.rtviwe.tport.model.Trip

@Dao
interface TripDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrip(trip: Trip): Long

    @Update
    fun updateTrip(trip: Trip)

    @Delete
    fun deleteTrip(trip: Trip)

//    @Query("SELECT * FROM trip")
//    fun getAllTrips(): LiveData<List<Trip>>
}