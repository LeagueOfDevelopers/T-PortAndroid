package com.lod.rtviwe.tport

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lod.rtviwe.tport.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun destinationsInput_SavedDestinationsOnRotate() {
        onView(withId(R.id.autocomplete_text_from_place)).perform(typeText("sample text1"))
        onView(withId(R.id.autocomplete_text_to_place)).perform(typeText("sample text2"))

        TestUtils.rotateScreen(activityRule)

        onView(withId(R.id.autocomplete_text_from_place)).check(matches(withText("sample text1")))
        onView(withId(R.id.autocomplete_text_to_place)).check(matches(withText("sample text2")))
    }

    @Test
    fun destinationsInput_SwappedDestinations() {
        onView(withId(R.id.autocomplete_text_from_place)).perform(typeText("sample text1"))
        onView(withId(R.id.autocomplete_text_to_place)).perform(typeText("sample text2"))

        onView(withId(R.id.image_button_swap)).perform(click())

        onView(withId(R.id.autocomplete_text_from_place)).check(matches(withText("sample text2")))
        onView(withId(R.id.autocomplete_text_to_place)).check(matches(withText("sample text1")))
    }
}