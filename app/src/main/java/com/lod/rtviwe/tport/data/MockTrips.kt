package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.model.*
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
                            "Краснодар",
                            "Krasnodar",
                            "KRD",
                            Coordinates(
                                5.6445,
                                0.9
                            )
                        ),
                        Calendar.getInstance().time
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
                                Calendar.getInstance().time
                            ),
                            Calendar.getInstance().time,
                            Calendar.getInstance().time
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
                                    "Moscow region",
                                    "Domodedovo",
                                    "DOM",
                                    Coordinates(
                                        45.12,
                                        45.54
                                    )
                                ),
                                Calendar.getInstance().time
                            ),
                            Calendar.getInstance().time,
                            Calendar.getInstance().time
                        )
                    ),
                    700.0,
                    Calendar.getInstance().time
                )
            )
        )
    }
}