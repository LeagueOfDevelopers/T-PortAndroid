package com.lod.rtviwe.tport

import android.view.KeyEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lod.rtviwe.tport.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun navigateToRegistration() {
        onView(withId(R.id.navigation_profile)).perform(click())
    }

    @Test
    fun rightPhoneNumberInput_PhoneNumberWithMask() {
        onView(withId(R.id.edit_text_phone_number)).perform(typeText("9999999999"))

        onView(withId(R.id.edit_text_phone_number)).check(matches(withText("+7 (999) 999 99 99")))
    }

    @Test
    fun rightPhoneNumberInput_SecondRegistrationStep() {
        onView(withId(R.id.edit_text_phone_number)).perform(typeText("79999999999"), closeSoftKeyboard())
        onView(withId(R.id.fab_register_step_one_next)).perform(click())

        onView(withId(R.id.text_view_did_send_code)).check(matches(isDisplayed()))
    }

    @Test
    fun wrongPhoneNumberInput_Error() {
        onView(withId(R.id.edit_text_phone_number)).perform(typeText("799"), closeSoftKeyboard())
        onView(withId(R.id.fab_register_step_one_next)).perform(click())

        onView(withId(R.id.edit_text_phone_number)).check(matches(hasErrorText("Неправильный номер")))
    }

    @Test
    fun codeInput_ThirdRegistrationStep() {
        onView(withId(R.id.edit_text_phone_number)).perform(typeText("9999999999"), closeSoftKeyboard())
        onView(withId(R.id.fab_register_step_one_next)).perform(click())
        onView(withId(R.id.group_code_input)).perform(click())

        onView(withId(R.id.group_code_input)).perform(
            pressKey(KeyEvent.KEYCODE_1),
            pressKey(KeyEvent.KEYCODE_1),
            pressKey(KeyEvent.KEYCODE_1),
            pressKey(KeyEvent.KEYCODE_1)
        )

//         TODO idling resource for checkCode
//        onView(withId(R.id.edit_text_enter_name)).check(matches(isDisplayed()))
    }

    @Test
    fun phoneNumberInput_SavePhoneOnRotate() {
        onView(withId(R.id.edit_text_phone_number)).perform(typeText("9999999999"), closeSoftKeyboard())
        onView(withId(R.id.edit_text_phone_number)).check(matches(withText("+7 (999) 999 99 99")))
        TestUtils.rotateScreen(activityRule)
        onView(withId(R.id.edit_text_phone_number)).check(matches(withText("+7 (999) 999 99 99")))
    }
}