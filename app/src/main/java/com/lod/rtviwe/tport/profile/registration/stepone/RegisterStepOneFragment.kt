package com.lod.rtviwe.tport.profile.registration.stepone

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.profile.registration.RegisterViewModel
import com.lod.rtviwe.tport.utils.setTextChangedListener
import kotlinx.android.synthetic.main.fragment_register_step_one.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepOneFragment : BaseFragment() {

    private val registerViewModel by sharedViewModel<RegisterViewModel>()

    private var phoneNumber = ""

    override fun getLayout() = R.layout.fragment_register_step_one

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (registerViewModel.isUserLogged()) {
            registerViewModel.navigateToProfileFragment(findNavController())
        }

        fab_register_step_one_next.setOnClickListener {
            if (registerViewModel.checkPhoneNumber(phoneNumber)) {
                registerViewModel.sendPhone(phoneNumber)
                setupNextStep()
            } else {
                showErrorPhoneNumber()
            }
        }

        edit_text_phone_number.setTextChangedListener {
            if (it.isNotEmpty()) {
                phoneNumber = it
            }
        }
        edit_text_phone_number.requestFocus()
    }

    private fun setupNextStep() {
        registerViewModel.navigateToSecondStep(findNavController(), phoneNumber)
    }

    private fun showErrorPhoneNumber() {
        edit_text_phone_number.error = getString(R.string.error_wrong_number)
    }

    companion object {

        const val ARGUMENT_PHONE_NUMBER = "REGISTER_PHONE_NUMBER_ARGUMENT"
    }
}
