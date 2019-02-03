package com.lod.rtviwe.tport

import androidx.test.espresso.IdlingResource

class RegistrationIdlingResource : IdlingResource {

    private lateinit var resourceCallback: IdlingResource.ResourceCallback

    override fun getName() = "REGISTRATION_IDLING_RESOURCE"

    override fun isIdleNow() = false // TODO

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        resourceCallback = callback
    }
}