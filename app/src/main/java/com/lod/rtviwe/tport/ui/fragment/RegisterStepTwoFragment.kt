package com.lod.rtviwe.tport.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.transaction
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.ui.activity.MainActivity
import com.lod.rtviwe.tport.viewmodel.RegisterViewModel
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.Mask
import com.redmadrobot.inputmask.model.CaretString
import kotlinx.android.synthetic.main.register_step_two_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepTwoFragment : BaseFragment() {

    companion object {

        fun newInstance(): RegisterStepTwoFragment {
            return RegisterStepTwoFragment()
        }

        var enteredCode = 0

        const val CODE_LENGTH = 4
    }

    private val registerViewModel by sharedViewModel<RegisterViewModel>()
    private val phoneNumberMask by inject<Mask>()

    override fun getLayout() = R.layout.register_step_two_fragment

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (enteredCode != 0) {
            edit_text_input_code.setText(enteredCode.toString())
        }

        super.onViewStateRestored(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val res = phoneNumberMask.apply(
            CaretString(
                RegisterStepOneFragment.enteredPhoneNumber.toString(),
                RegisterStepOneFragment.enteredPhoneNumber.toString().length
            ),
            true
        )
        text_view_register_step_two_phone_number.text = res.formattedText.string

        edit_text_input_code.hint = MaskedTextChangedListener.installOn(
            edit_text_input_code,
            "[0]     [0]     [0]     [0]",
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(maskFilled: Boolean, extractedValue: String) {
                    try {
                        tryToGetIntFrom(extractedValue)
                    } catch (error: NumberFormatException) {
                        showError()
                    }

                    if (checkCodeLength(enteredCode)) {
                        registerViewModel.checkCode(enteredCode)
                        setupNextStep()
                    }
                }
            }).placeholder()
        edit_text_input_code.requestFocus()
    }

    override fun scrollToTop() {
        scroll_view_step_two.smoothScrollTo(0, 0)
    }

    private fun tryToGetIntFrom(string: String) {
        if (string.isNotEmpty()) {
            enteredCode = string.toInt()
        }
    }

    private fun showError() {
        Log.e("RegisterStepTwoFragment", "Wrong code input")
        edit_text_input_code.error = getString(R.string.error_wrong_code)
    }

    private fun checkCodeLength(code: Int) = code.toString().length == CODE_LENGTH

    private fun setupNextStep() {
        activity?.supportFragmentManager?.transaction(allowStateLoss = true) {
            replace(R.id.main_container, RegisterStepThreeFragment.newInstance())
            MainActivity.currentRegistrationStep = 3
        }
    }
}