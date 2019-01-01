package com.lod.rtviwe.tport.search

interface SearchListener {

    fun onPickUpButton(fromPlace: String, toPlace: String, travelTime: String)
}