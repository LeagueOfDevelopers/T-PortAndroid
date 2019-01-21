package com.lod.rtviwe.tport.profile.registration

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.network.register.LoginConfirmationRequest
import com.lod.rtviwe.tport.utils.AuthService
import com.lod.rtviwe.tport.utils.RouteIcons
import com.lod.rtviwe.tport.utils.toPhone
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.Mask
import com.redmadrobot.inputmask.model.CaretString
import kotlinx.android.synthetic.main.register_step_two_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RegisterStepTwoFragment : BaseFragment() {

    private val registerViewModel by sharedViewModel<RegisterViewModel>()
    private val phoneNumberMask by inject<Mask>()
    private val authService by inject<AuthService>()

    private lateinit var navController: NavController

    private var phoneNumber = ""
    private var code = ""

    override fun getLayout() = R.layout.register_step_two_fragment

    override fun scrollToTop() {
        scroll_view_step_two.smoothScrollTo(0, 0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.also {
            it.getString(RegisterStepOneFragment.ARGUMENT_PHONE_NUMBER)?.let { phone -> phoneNumber = phone }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            navController = Navigation.findNavController(it, R.id.nav_host_fragment)
        }

        val maskResult = phoneNumberMask.apply(
            CaretString(phoneNumber, phoneNumber.length),
            true
        )
        text_view_register_step_two_phone_number.text = maskResult.formattedText.string

        edit_text_input_code.setText(code)

        val maskTextChangeListener = object : MaskedTextChangedListener.ValueListener {

            override fun onTextChanged(maskFilled: Boolean, extractedValue: String) {
                handleCodeChange(extractedValue)
            }
        }

        edit_text_input_code.hint = MaskedTextChangedListener.installOn(
            edit_text_input_code,
            "[0][0][0][0]",
            maskTextChangeListener
        ).placeholder()

        edit_text_input_code.requestFocus()
        showKeyboard()

        group_code_input.setOnClickListener {
            showKeyboard()
        }
    }

    private fun handleCodeChange(newText: String) {
        updateCodeImages(newText)

        if (checkCodeLength(code)) {
            registerViewModel.login(
                LoginConfirmationRequest(phoneNumber.toPhone(), code), { token ->
                    if (activity != null) {
                        authService.putToken(token)
                    } else {
                        Timber.e("Fragment has been closed")
                    }

                    setupNextStep()
                }, {
                    Toast.makeText(context, getString(R.string.error_wrong_code), Toast.LENGTH_SHORT).show()
                })
        }
    }

    private fun showError() {
        Toast.makeText(context, getString(R.string.error_wrong_code), Toast.LENGTH_SHORT).show()
    }

    private fun checkCodeLength(code: String) = (code.length == CODE_LENGTH)

    private fun setupNextStep() {
        activity?.let {
            val bundle = Bundle().apply { putString(RegisterStepTwoFragment.ARGUMENT_PHONE_NUMBER, phoneNumber) }
            navController.navigate(R.id.action_registerStepTwoFragment_to_registerStepThreeFragment, bundle)
        }
    }

    private fun updateCodeImages(text: String) {
        try {
            code = text

            text.forEachIndexed { index, char ->
                val numberImageResource = RouteIcons.getNumberDrawable(char)
                getImageViewCode(index).setImageResource(numberImageResource)
            }

            (text.length until CODE_LENGTH).forEach { index ->
                getImageViewCode(index).setImageResource(R.drawable.code_placeholder)
            }
        } catch (error: NumberFormatException) {
            showError()
        }
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

    companion object {

        const val CODE_LENGTH = 4
        const val ARGUMENT_PHONE_NUMBER = "PHONE_NUMBER_ARGUMENT"
    }
}