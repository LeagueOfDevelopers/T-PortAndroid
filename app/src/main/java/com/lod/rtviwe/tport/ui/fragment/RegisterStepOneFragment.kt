package com.lod.rtviwe.tport.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.transaction
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.ui.activity.MainActivity
import com.lod.rtviwe.tport.viewmodel.RegisterViewModel
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.register_step_one_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class RegisterStepOneFragment : BaseFragment() {

    companion object {

        fun newInstance(): RegisterStepOneFragment {
            return RegisterStepOneFragment()
        }

        var enteredPhoneNumber = 0L
    }

    private val registerViewModel by sharedViewModel<RegisterViewModel>()

    override fun getLayout() = R.layout.register_step_one_fragment

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (enteredPhoneNumber != 0L) {
            edit_text_phone_number.setText(enteredPhoneNumber.toString())
        }

        super.onViewStateRestored(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_register_step_one_continue.setOnClickListener {
            activity?.supportFragmentManager?.transaction(allowStateLoss = true) {
                replace(R.id.main_container, RegisterStepTwoFragment.newInstance())
                MainActivity.currentRegistrationStep = 2
                enteredPhoneNumber = ("+7$enteredPhoneNumber").toLong()
                registerViewModel.sendCodeToPhoneNumber(enteredPhoneNumber)
            }
        }

        edit_text_phone_number.hint = MaskedTextChangedListener.installOn(
            edit_text_phone_number,
            "+7 ([000]) [000]-[00]-[00]",
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(maskFilled: Boolean, extractedValue: String) {
                    if (extractedValue.isNotEmpty()) {
                        enteredPhoneNumber = extractedValue.toLong()
                    }
                }
            }).placeholder()
        edit_text_phone_number.requestFocus()
    }

    override fun scrollToTop() {

    }
}