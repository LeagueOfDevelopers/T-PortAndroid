package com.lod.rtviwe.tport.profile.registration

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.listeners.RegisterStepTwoListener
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.Mask
import com.redmadrobot.inputmask.model.CaretString
import kotlinx.android.synthetic.main.register_step_two_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RegisterStepTwoFragment : BaseFragment() {

    companion object {

        fun newInstance(phoneNumber: String, code: String): RegisterStepTwoFragment {
            val newArguments = Bundle().apply {
                putString(STATE_PHONE_NUMBER, phoneNumber)
                putString(STATE_CODE, code)
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

    private lateinit var listenerStepTwo: RegisterStepTwoListener

    private var phoneNumber = ""
    private var code = ""

    override fun getLayout() = R.layout.register_step_two_fragment

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is RegisterStepTwoListener -> listenerStepTwo = context
            else -> throw ClassCastException("$context does not implements RegisterStepTwoListener")
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.let {
            if (it.containsKey(STATE_PHONE_NUMBER)) {
                phoneNumber = it.getString(STATE_PHONE_NUMBER)
            }
            if (it.containsKey(STATE_CODE)) {
                code = it.getString(STATE_CODE)
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val res = phoneNumberMask.apply(
            CaretString(
                phoneNumber,
                phoneNumber.length
            ),
            true
        )
        text_view_register_step_two_phone_number.text = res.formattedText.string

        edit_text_input_code.setText(code)

        edit_text_input_code.hint = MaskedTextChangedListener.installOn(
            edit_text_input_code,
            "[0][0][0][0]",
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(maskFilled: Boolean, extractedValue: String) {
                    try {
                        code = extractedValue
                        listenerStepTwo.saveCode(extractedValue)

                        extractedValue.forEachIndexed { index, char ->
                            val numberImageResource = getNumberDrawable(char)
                            getImageViewCode(index).setImageResource(numberImageResource)
                        }

                        (extractedValue.length until CODE_LENGTH).forEach { index ->
                            getImageViewCode(index).setImageResource(R.drawable.code_placeholder)
                        }
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
        showKeyboard()

        image_view_code_first.setOnClickListener {
            showKeyboard()
        }

        image_view_code_second.setOnClickListener {
            showKeyboard()
        }

        image_view_code_third.setOnClickListener {
            showKeyboard()
        }

        image_view_code_fourth.setOnClickListener {
            showKeyboard()
        }
    }

    private fun showError() {
        Timber.e("Wrong code input")
        Toast.makeText(context, getString(R.string.error_wrong_code), Toast.LENGTH_SHORT).show()
    }

    private fun checkCodeLength(code: String) = (code.length == CODE_LENGTH)

    private fun setupNextStep() {
        listenerStepTwo.onRegisterStepTwoContinue()
    }

    private fun showKeyboard() {
        edit_text_input_code.post {
            activity?.let {
                val imm = it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(edit_text_input_code, 0)
            }
        }
    }

    private fun getImageViewCode(index: Int) = when (index) {
        0 -> image_view_code_first
        1 -> image_view_code_second
        2 -> image_view_code_third
        3 -> image_view_code_fourth
        else -> throw IndexOutOfBoundsException("Wrong image view code index")
    }

    private fun getNumberDrawable(char: Char) = when (char) {
        '0' -> R.drawable._0
        '1' -> R.drawable._1
        '2' -> R.drawable._2
        '3' -> R.drawable._3
        '4' -> R.drawable._4
        '5' -> R.drawable._5
        '6' -> R.drawable._6
        '7' -> R.drawable._7
        '8' -> R.drawable._8
        '9' -> R.drawable._9
        else -> R.drawable.code_placeholder
    }
}