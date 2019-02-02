package com.lod.rtviwe.tport

import android.view.KeyEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lod.rtviwe.tport.main.MainActivity
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.BeforeClass

@RunWith(AndroidJUnit4::class)
class AutocompleteTest {

    @get:Rule
    private val rule = ActivityTestRule(MainActivity::class.java)

    // https://stackoverflow.com/questions/36382596/espresso-how-to-switch-typetext-to-english-or-other-languages-input-mode
    @Test
    fun testAutoComplete_Moscow() {
        rule.launchActivity(null)
        onView(withId(R.id.autocomplete_text_from_place)).perform(click())
        onView(withId(R.id.autocomplete_text_from_place)).check(matches(withText("")))

        // TODO find a way to type russian letters
        onView(withId(R.id.autocomplete_text_from_place)).perform(replaceText("ос"))
        onView(withId(R.id.autocomplete_text_from_place)).perform(
            pressKey(KeyEvent.KEYCODE_LANGUAGE_SWITCH),
            pressKey(KeyEvent.KEYCODE_M)
        )

        onView(withText("г Москва")).inRoot(withDecorView(not(`is`(rule.activity.window.decorView)))).perform(click())
        onView(withId(R.id.autocomplete_text_from_place)).check(matches(withText("г Москва")))
    }
}