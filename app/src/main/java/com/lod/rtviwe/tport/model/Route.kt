package com.lod.rtviwe.tport.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Route")
@Parcelize
data class Route(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "id_to_trip")
    var tripId: Long,
    var vehicleName: String,
    var type: RouteType,
    var cost: Int = 0,
    @Embedded
    var destination: Destination,
    var isPaid: Boolean = false
) : Parcelable {

    @Ignore
    constructor(
        tripId: Long,
        vehicleName: String,
        type: RouteType,
        cost: Int,
        destination: Destination,
        isPaid: Boolean
    ) : this(0, tripId, vehicleName, type, cost, destination, isPaid)
}