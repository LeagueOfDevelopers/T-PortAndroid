//package com.lod.rtviwe.tport.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverters
//import com.lod.rtviwe.tport.database.converter.RouteTypeConverter
//import com.lod.rtviwe.tport.database.dao.*
//import com.lod.rtviwe.tport.model.Destination
//import com.lod.rtviwe.tport.model.Place
//import com.lod.rtviwe.tport.model.Route
//import com.lod.rtviwe.tport.model.Trip
//
//@Database(
//    entities = [
//        Trip::class,
//        Route::class,
//        Destination::class,
//        Place::class
//    ],
//    version = 1,
//    exportSchema = false
//)
//@TypeConverters(RouteTypeConverter::class)
//abstract class TripDatabase : RoomDatabase() {
//
//    abstract fun tripDao(): TripDao
//    abstract fun userDao(): UserDao
//    abstract fun placeDao(): PlaceDao
//    abstract fun routeDao(): RouteDao
//    abstract fun fullTripDao(): FullTripDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: TripDatabase? = null
//
//        fun getDatabase(context: Context): TripDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TripDatabase::class.java,
//                    "TPort.db"
//                )
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}