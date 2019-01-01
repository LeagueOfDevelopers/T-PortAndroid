package com.lod.rtviwe.tport.profile.registration

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.listeners.RegisterStepOneListener
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.register_step_one_fragment.*

class RegisterStepOneFragment : BaseFragment() {

    companion object {

        fun newInstance(phoneNumber: String): RegisterStepOneFragment {
            val newArguments = Bundle().apply {
                putString(STATE_PHONE_NUMBER, phoneNumber)
            }
            return RegisterStepOneFragment().apply {
                arguments = newArguments
            }
        }

        private const val STATE_PHONE_NUMBER = "PHONE_NUMBER_STEP_ONE_STATE"
        const val PHONE_NUMBER_LENGTH = 10
    }

    private lateinit var listenerStepOne: RegisterStepOneListener
    private var phoneNumber = ""

    override fun getLayout() = R.layout.register_step_one_fragment

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is RegisterStepOneListener -> listenerStepOne = context
            else -> throw ClassCastException("$context does not implements RegisterStepOneListener")
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.let {
            if (it.containsKey(STATE_PHONE_NUMBER)) {
                phoneNumber = it.getString(STATE_PHONE_NUMBER)
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!phoneNumber.isEmpty()) {
            edit_text_phone_number.setText(phoneNumber)
        }

        button_register_step_one_continue.setOnClickListener {
            if (checkPhoneNumber(phoneNumber)) {
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
                        listenerStepOne.savePhoneNumber(phoneNumber)
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
}