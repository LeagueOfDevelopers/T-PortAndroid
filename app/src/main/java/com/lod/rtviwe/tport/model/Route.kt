package com.lod.rtviwe.tport.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.PrimaryKey

//@Entity(tableName = "Route")
data class Route(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_route")
    var id: Long,
    @ColumnInfo(name = "id_to_trip")
    var tripId: Long,
    @ColumnInfo(name = "route_vehicle_name")
    var vehicleName: String,
    @ColumnInfo(name = "route_type")
    var type: RouteType,
    @ColumnInfo(name = "route_cost")
    var cost: Int = 0,
    @Embedded
    var destination: Destination,
    @ColumnInfo(name = "route_is_paid")
    var isPaid: Boolean = false
) {

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