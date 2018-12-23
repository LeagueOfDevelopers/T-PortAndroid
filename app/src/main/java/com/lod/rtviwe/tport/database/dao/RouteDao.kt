package com.lod.rtviwe.tport.database.dao

import androidx.room.*
import com.lod.rtviwe.tport.model.Route

@Dao
interface RouteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoute(route: Route): Long

    @Update
    fun updateRoute(route: Route)

    @Delete
    fun deleteRoute(route: Route)

//    @Query("SELECT * FROM route")
//    fun getAllRoutes(): LiveData<List<Route>>
}