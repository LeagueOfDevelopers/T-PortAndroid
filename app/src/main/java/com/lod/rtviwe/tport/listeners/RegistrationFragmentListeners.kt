package com.lod.rtviwe.tport.listeners

interface RegisterStepOneListener {

    fun onRegisterStepOneContinue(phoneNumber: Long)

    fun savePhoneNumber(phoneNumber: Long)
}

interface RegisterStepTwoListener {

    fun onRegisterStepTwoContinue()

    fun saveCode(code: String)
}

interface RegisterStepThreeListener {

    fun onRegisterStepThreeContinue()
}
