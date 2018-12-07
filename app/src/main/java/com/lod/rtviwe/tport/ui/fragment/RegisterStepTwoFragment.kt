package com.lod.rtviwe.tport.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.ui.listeners.OnRegisterStepTwoListener
import com.lod.rtviwe.tport.viewmodel.RegisterViewModel
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.Mask
import com.redmadrobot.inputmask.model.CaretString
import kotlinx.android.synthetic.main.register_step_two_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepTwoFragment : BaseFragment() {

    companion object {

        fun newInstance(phoneNumber: Long, code: Int): RegisterStepTwoFragment {
            val newArguments = Bundle().apply {
                putLong(STATE_PHONE_NUMBER, phoneNumber)
                putInt(STATE_CODE, code)
            }
            return RegisterStepTwoFragment().apply {
                arguments = newArguments
            }
        }

        private const val STATE_PHONE_NUMBER = "PHONE_NUMBER_STEP_TWO_STATE"
        private const val STATE_CODE = "CODE_STEP_TWO_STATE"

        const val CODE_LENGTH = 4
    }

    private val registerViewModel by sharedViewModel<RegisterViewModel>()
    private val phoneNumberMask by inject<Mask>()

    private lateinit var listenerStepTwo: OnRegisterStepTwoListener

    private var phoneNumber = 0L
    private var code = 0

    override fun getLayout() = R.layout.register_step_two_fragment

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is OnRegisterStepTwoListener -> listenerStepTwo = context
            else -> throw ClassCastException("$context does not implements OnRegisterStepTwoListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.let {
            if (it.containsKey(STATE_PHONE_NUMBER)) {
                phoneNumber = it.getLong(STATE_PHONE_NUMBER)
            }
            if (it.containsKey(STATE_CODE)) {
                code = it.getInt(STATE_CODE)
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val res = phoneNumberMask.apply(
            CaretString(
                phoneNumber.toString(),
                phoneNumber.toString().length
            ),
            true
        )
        text_view_register_step_two_phone_number.text = res.formattedText.string

        if (code != 0) {
            edit_text_input_code.setText("$code")
        }

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

                    if (checkCodeLength(code)) {
                        registerViewModel.checkCode(code)
                        setupNextStep()
                    }
                }
            }).placeholder()
        edit_text_input_code.requestFocus()
    }

    private fun tryToGetIntFrom(string: String) {
        if (string.isNotEmpty()) {
            code = string.toInt()
            listenerStepTwo.saveCode(code)
        }
    }

    private fun showError() {
        Log.e("RegisterStepTwoFragment", "Wrong code input")
        edit_text_input_code.error = getString(R.string.error_wrong_code)
    }

    private fun checkCodeLength(code: Int) = code.toString().length == CODE_LENGTH

    private fun setupNextStep() {
        listenerStepTwo.onRegisterStepTwoContinue()
    }
}