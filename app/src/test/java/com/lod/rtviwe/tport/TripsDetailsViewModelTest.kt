package com.lod.rtviwe.tport

import android.app.Application
import com.lod.rtviwe.tport.data.MockTrips
import com.lod.rtviwe.tport.tripdetails.TripDetailsViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class TripDetailsViewModelTest {

    @MockK
    lateinit var application: Application

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun populateAdapter_ItemsInAdapter() {
        val viewModel = TripDetailsViewModel(application)
        val adapter = GroupAdapter<ViewHolder>()
        val trip = MockTrips.getItems()[0]

        viewModel.populateAdapter(adapter, trip)

//        verify(exactly = ) { adapter.add(any()) }
    }
}