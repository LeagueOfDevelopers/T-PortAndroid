package com.lod.rtviwe.tport.profile.registration

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.profile.registration.stepone.RegisterStepOneFragment
import com.lod.rtviwe.tport.profile.registration.stepone.SendPhoneNetworkDataSource
import com.lod.rtviwe.tport.profile.registration.stepone.SendPhoneRequest
import com.lod.rtviwe.tport.profile.registration.stepthree.SendNameNetworkDataSource
import com.lod.rtviwe.tport.profile.registration.stepthree.SendNameRequest
import com.lod.rtviwe.tport.profile.registration.steptwo.RegisterStepTwoFragment
import com.lod.rtviwe.tport.profile.registration.steptwo.SendCodeDataSource
import com.lod.rtviwe.tport.profile.registration.steptwo.SendCodeNetworkDataSource
import com.lod.rtviwe.tport.profile.registration.steptwo.SendCodeRequest
import com.lod.rtviwe.tport.utils.AuthService
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class RegisterViewModel(private val app: Application) : AndroidViewModel(app), KoinComponent {

    private val authService by inject<AuthService>()
    private val sendPhoneNetworkDataSource by inject<SendPhoneNetworkDataSource>()
    private val sendNameNetworkDataSource by inject<SendNameNetworkDataSource>()
    private val sendCodeNetworkDataSource by inject<SendCodeNetworkDataSource>()

    override fun onCleared() {
        super.onCleared()
        sendPhoneNetworkDataSource.clear()
    }

    fun sendCode(codeRequest: SendCodeRequest, onSuccess: () -> Unit) {
        sendCodeNetworkDataSource.sendCode(codeRequest, object : SendCodeDataSource.SendCodeCallback {
            override fun success(token: String) {
                authService.putToken(token)
                onSuccess()
            }

            override fun fail() {
                Toast.makeText(app.baseContext, app.getString(R.string.error_wrong_code), Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun sendPhone(phone: String) {
        sendPhoneNetworkDataSource.sendPhone(SendPhoneRequest(phone))
    }

    fun sendName(phoneNumber: String, name: String) {
        sendNameNetworkDataSource.sendName(SendNameRequest(name, phoneNumber))
    }

    fun navigateToSecondStep(navController: NavController, phoneNumber: String) {
        val bundle = Bundle().apply { putString(RegisterStepOneFragment.ARGUMENT_PHONE_NUMBER, phoneNumber) }
        navController.navigate(R.id.action_registerStepOneFragment_to_registerStepTwoFragment, bundle)
    }

    fun navigateToThirdStep(navController: NavController, phoneNumber: String) {
        val bundle = Bundle().apply { putString(RegisterStepTwoFragment.ARGUMENT_PHONE_NUMBER, phoneNumber) }
        navController.navigate(R.id.action_registerStepTwoFragment_to_registerStepThreeFragment, bundle)
    }

    fun navigateToProfileFragment(navController: NavController) {
        navController.navigate(R.id.action_global_profileFragment)
    }

    fun checkPhoneNumber(phoneNumber: String) = phoneNumber.length == PHONE_NUMBER_LENGTH

    fun checkCodeLength(code: String) = (code.length == RegisterStepTwoFragment.CODE_LENGTH)

    // TODO replace it back
    fun isUserLogged() = false /*authService.getToken(context!!) != null*/

    companion object {

        private const val PHONE_NUMBER_LENGTH = 18
    }
}