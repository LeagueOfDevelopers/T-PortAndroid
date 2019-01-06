package com.lod.rtviwe.tport

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented AutocompleteSuggestions, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under AutocompleteSuggestions.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.lod.rtviwe.tport", appContext.packageName)
    }
}
