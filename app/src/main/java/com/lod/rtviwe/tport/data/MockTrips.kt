package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.model.*
import org.joda.time.Duration
import java.util.*

object MockTrips : TripsProvider {

    override fun getItems() = data

    private val data = MutableLiveData<MutableList<Trip>>().apply {
        postValue(
            mutableListOf(
                Trip(
                    "stub id",
                    Destination(
                        Place(
                            "Москва",
                            "Moscow",
                            "MOW",
                            Coordinates(
                                53.35,
                                5.2
                            )
                        ),
                        Place(
                            "Ростов-на-Дону",
                            "Rostov",
                            "ROS",
                            Coordinates(
                                5.6445,
                                0.9
                            )
                        ),
                        Duration.standardHours(5)
                    ),
                    listOf(
                        Route(
                            "stub id",
                            Transport(
                                "UberX",
                                TransportationType.TAXI
                            ),
                            350.0,
                            Destination(
                                Place(
                                    "Горняк-2",
                                    "Moscow",
                                    "MOW",
                                    Coordinates(
                                        52.0,
                                        69.69
                                    )
                                ),
                                Place(
                                    "Домодедово",
                                    "MOW",
                                    "DOM",
                                    Coordinates(
                                        69.69,
                                        14.88
                                    )
                                ),
                                Duration.standardHours(1)
                            ),
                            Calendar.getInstance().time,
                            Calendar.getInstance().time,
                            true
                        ),
                        Route(
                            "stub id",
                            Transport(
                                "Aeroflot",
                                TransportationType.AIRPLANE
                            ),
                            3500.0,
                            Destination(
                                Place(
                                    "Домодедово",
                                    "Moscow",
                                    "MOW",
                                    Coordinates(
                                        45.12,
                                        45.54
                                    )
                                ),
                                Place(
                                    "Платов",
                                    "Rostov",
                                    "ROS",
                                    Coordinates(
                                        45.12,
                                        45.54
                                    )
                                ),
                                Duration.standardHours(4)
                            ),
                            Calendar.getInstance().time,
                            Calendar.getInstance().time
                        )
                    ),
                    700.0
                )
            )
        )
    }
}