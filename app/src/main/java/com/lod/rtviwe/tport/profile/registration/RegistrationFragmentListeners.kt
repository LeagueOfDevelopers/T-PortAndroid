package com.lod.rtviwe.tport.profile.registration

interface RegisterStepOneListener {

    fun onRegisterStepOneContinue(phoneNumber: String)
}

interface RegisterStepTwoListener {

    fun onRegisterStepTwoContinue()
}

interface RegisterStepThreeListener {

    fun onRegisterStepThreeContinue()
}
