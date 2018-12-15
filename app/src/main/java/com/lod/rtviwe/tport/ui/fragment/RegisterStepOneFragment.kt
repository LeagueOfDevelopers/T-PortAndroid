package com.lod.rtviwe.tport.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.network.RegistrationApi
import com.lod.rtviwe.tport.ui.listeners.OnRegisterStepOneListener
import com.lod.rtviwe.tport.viewmodel.RegisterViewModel
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.register_step_one_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepOneFragment : BaseFragment() {

    companion object {

        fun newInstance(phoneNumber: Long): RegisterStepOneFragment {
            val newArguments = Bundle().apply {
                putLong(STATE_PHONE_NUMBER, phoneNumber)
            }
            return RegisterStepOneFragment().apply {
                arguments = newArguments
            }
        }

        private const val STATE_PHONE_NUMBER = "PHONE_NUMBER_STEP_ONE_STATE"
        const val PHONE_NUMBER_LENGTH = 10
    }

    private val registerViewModel by sharedViewModel<RegisterViewModel>()
    private val registrationApi by inject<RegistrationApi>()

    private lateinit var listenerStepOne: OnRegisterStepOneListener
    private var phoneNumber = 0L

    override fun getLayout() = R.layout.register_step_one_fragment

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is OnRegisterStepOneListener -> listenerStepOne = context
            else -> throw ClassCastException("$context does not implements OnRegisterStepOneListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.let {
            if (it.containsKey(STATE_PHONE_NUMBER)) {
                phoneNumber = it.getLong(STATE_PHONE_NUMBER)
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (phoneNumber != 0L) {
            edit_text_phone_number.setText("$phoneNumber")
        }

        button_register_step_one_continue.setOnClickListener {
            if (checkPhoneNumber(phoneNumber)) {
                setupNextStep()
            } else {
                showErrorPhoneNumber()
            }
        }

        edit_text_phone_number.hint = MaskedTextChangedListener.installOn(
            edit_text_phone_number,
            "+7 ([000]) [000]-[00]-[00]",
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(maskFilled: Boolean, extractedValue: String) {
                    if (extractedValue.isNotEmpty()) {
                        phoneNumber = extractedValue.toLong()
                        listenerStepOne.savePhoneNumber(phoneNumber)
                    }
                }
            }).placeholder()
        edit_text_phone_number.requestFocus()
    }

    private fun checkPhoneNumber(phoneNumber: Long) = phoneNumber.toString().length == PHONE_NUMBER_LENGTH

    private fun setupNextStep() {
        registerViewModel.sendCodeToPhoneNumber(phoneNumber, registrationApi)
        listenerStepOne.onRegisterStepOneContinue(phoneNumber)
    }

    private fun showErrorPhoneNumber() {
        edit_text_phone_number.error = getString(R.string.error_wrong_number)
    }
}