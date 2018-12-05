package com.lod.rtviwe.tport.ui.listeners

interface OnRegisterStepOneListener {

    fun onRegisterStepOneContinue(phoneNumber: Long)

    fun savePhoneNumber(phoneNumber: Long)
}

interface OnRegisterStepTwoListener {

    fun onRegisterStepTwoContinue()

    fun saveCode(code: Int)
}

interface OnRegisterStepThreeListener {

    fun onRegisterStepThreeContinue()
}
