package com.lod.rtviwe.tport

import android.app.Application
import com.lod.rtviwe.tport.data.MockTrips
import com.lod.rtviwe.tport.model.Destination
import com.lod.rtviwe.tport.model.Place
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.tripdetails.TripDetailsViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class TripDetailsViewModelTest {

    @MockK
    lateinit var application: Application

    // Cannot create instance so have to mock
    @MockK
    lateinit var adapter: GroupAdapter<ViewHolder>

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun populateAdapter_ItemsInAdapter() {
        every { adapter.add(any()) } just Runs
        val viewModel = TripDetailsViewModel(application)
        val trip = MockTrips.getItems()[0]

        viewModel.populateAdapter(adapter, trip)

        verify(exactly = 9) { adapter.add(any()) }
    }

    @Test
    fun populateAdapter_EmptyAdapter() {
        every { adapter.add(any()) } just Runs
        val viewModel = TripDetailsViewModel(application)
        val trip = Trip(
            "",
            Destination(
                Place("", "", "", null),
                Place("", "", "", null)
            ),
            listOf(),
            0.0
        )

        viewModel.populateAdapter(adapter, trip)

        verify(exactly = 0) { adapter.add(any()) }
    }
}