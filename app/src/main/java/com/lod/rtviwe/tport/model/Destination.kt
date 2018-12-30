package com.lod.rtviwe.tport.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "Destination")
@Parcelize
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
) : Parcelable {

    @Ignore
    constructor(placeFrom: Place, placeTo: Place, arrivalDate: Date) : this(0, placeFrom, placeTo, arrivalDate)
}