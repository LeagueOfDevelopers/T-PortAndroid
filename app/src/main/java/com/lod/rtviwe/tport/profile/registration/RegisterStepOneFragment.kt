package com.lod.rtviwe.tport.profile.registration

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.network.register.LoginRequest
import com.lod.rtviwe.tport.utils.toPhone
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.register_step_one_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepOneFragment : BaseFragment() {

    private val registerViewModel by sharedViewModel<RegisterViewModel>()

    private lateinit var listenerStepOne: RegisterStepOneListener
    private var phoneNumber = ""

    override fun getLayout() = R.layout.register_step_one_fragment

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is RegisterStepOneListener -> listenerStepOne = context
            else -> throw ClassCastException("$context does not implement RegisterStepOneListener")
        }
    }

    // TODO remove
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_register_step_one_continue.setOnClickListener {
            if (checkPhoneNumber(phoneNumber)) {
                registerViewModel.sendPhone(LoginRequest(phoneNumber.toPhone()))
                setupNextStep()
            } else {
                showErrorPhoneNumber()
            }
        }

        // TODO remove
        edit_text_phone_number.setText("8005553535")

        edit_text_phone_number.hint = MaskedTextChangedListener.installOn(
            edit_text_phone_number,
            "+7 ([000]) [000]-[00]-[00]",
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(maskFilled: Boolean, extractedValue: String) {
                    if (extractedValue.isNotEmpty()) {
                        phoneNumber = extractedValue
//                        listenerStepOne.savePhoneNumber(phoneNumber) send phonenumber via action(?)
                    }
                }
            }).placeholder()
        edit_text_phone_number.requestFocus()
    }

    private fun setupNextStep() {
        listenerStepOne.onRegisterStepOneContinue(phoneNumber)
    }

    private fun showErrorPhoneNumber() {
        edit_text_phone_number.error = getString(R.string.error_wrong_number)
    }

    private fun checkPhoneNumber(phoneNumber: String) = phoneNumber.length == PHONE_NUMBER_LENGTH

    companion object {

        const val ARGUMENT_PHONE_NUMBER = "REGISTER_PHONE_NUMBER_ARGUMENT"
        const val PHONE_NUMBER_LENGTH = 10
    }
}