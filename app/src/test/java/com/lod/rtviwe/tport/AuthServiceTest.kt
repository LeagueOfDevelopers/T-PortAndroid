package com.lod.rtviwe.tport

import android.content.Context
import com.lod.rtviwe.tport.utils.AuthService
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AuthServiceTest {

    @MockK
    lateinit var context: Context

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun checkCodeLength_WithLengthFour() {
        val authService = AuthService(context)
        val code = "1111"
        assertEquals(true, authService.checkCodeLength(code))
    }

    @Test
    fun checkCodeLength_WithLengthOne() {
        val authService = AuthService(context)
        val code = "8"
        assertEquals(false, authService.checkCodeLength(code))
    }

    @Test
    fun checkCodeLength_EmptyString() {
        val authService = AuthService(context)
        val code = ""
        assertEquals(false, authService.checkCodeLength(code))
    }

    @Test
    fun checkPhoneNumber_CorrectPhoneNumber() {
        val authService = AuthService(context)
        val phone = "+7 (999) 999 99 99"
        assertEquals(true, authService.checkPhoneNumber(phone))
    }

    @Test
    fun checkPhoneNumber_WrongPhoneNumber() {
        val authService = AuthService(context)
        val phone = "+7 (999) 999"
        assertEquals(false, authService.checkPhoneNumber(phone))
    }

    @Test
    fun checkPhoneNumber_EmptyString() {
        val authService = AuthService(context)
        val phone = ""
        assertEquals(false, authService.checkPhoneNumber(phone))
    }
}