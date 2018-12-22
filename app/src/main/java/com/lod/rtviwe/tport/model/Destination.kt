package com.lod.rtviwe.tport.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "Destination")
data class Destination(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "destination_id")
    var id: Long,
    @Embedded(prefix = "from_")
    var placeFrom: Place,
    @Embedded(prefix = "to_")
    var placeTo: Place,
    @ColumnInfo(name = "arrival_date")
    var arrivalDate: Date
) {

    @Ignore
    constructor(placeFrom: Place, placeTo: Place, arrivalDate: Date) : this(0, placeFrom, placeTo, arrivalDate)
}