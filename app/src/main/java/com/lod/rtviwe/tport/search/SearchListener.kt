package com.lod.rtviwe.tport.search

import com.lod.rtviwe.tport.search.searchbox.DestinationWord

interface SearchListener {

    fun onPickUpButton(fromPlace: DestinationWord, toPlace: DestinationWord, travelTime: String)
}