package com.lod.rtviwe.tport.model

import androidx.room.*
import java.util.*

@Entity(tableName = "Trip")
data class Trip(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_trip")
    var id: Long,
    @Embedded(prefix = "from_")
    var placeFrom: Place,
    @Embedded(prefix = "to_")
    var placeTo: Place,
    @ColumnInfo(name = "trip_cost")
    var cost: Int,
    @ColumnInfo(name = "trip_time_travel")
    var timeTravel: Date
) {

    @Ignore
    constructor(placeFrom: Place, placeTo: Place, cost: Int, timeTravel: Date) : this(
        0,
        placeFrom,
        placeTo,
        cost,
        timeTravel
    )
}