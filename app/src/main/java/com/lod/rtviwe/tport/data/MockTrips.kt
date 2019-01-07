package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.model.*
import org.joda.time.DateTime

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
                        )
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
                                )
                            ),
                            DateTime(),
                            DateTime().plusHours(1),
                            true
                        ),
                        Route(
                            "stub id",
                            Transport(
                                "Аэрофлот",
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
                                )
                            ),
                            DateTime().plusHours(1),
                            DateTime().plusHours(2)
                        ),
                        Route(
                            "stub id",
                            Transport(
                                "РЖД",
                                TransportationType.TRAIN
                            ),
                            1200.0,
                            Destination(
                                Place(
                                    "Платов",
                                    "Rostov",
                                    "ROS",
                                    Coordinates(
                                        45.12,
                                        45.54
                                    )
                                ),
                                Place(
                                    "Ростов-на-Дону",
                                    "Rostov",
                                    "ROS",
                                    Coordinates(
                                        45.12,
                                        45.54
                                    )
                                )
                            ),
                            DateTime().plusHours(2),
                            DateTime().plusHours(3)
                        )
                    ),
                    700.0
                )
            )
        )
    }
}