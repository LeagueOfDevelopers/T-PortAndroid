package com.lod.rtviwe.tport.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.transaction
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.ui.activity.MainActivity
import com.lod.rtviwe.tport.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.register_step_two_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepTwoFragment : BaseFragment() {

    companion object {

        fun newInstance(): RegisterStepTwoFragment {
            return RegisterStepTwoFragment()
        }

        var enteredCode = 0
    }

    private val registerViewModel by sharedViewModel<RegisterViewModel>()

    override fun getLayout() = R.layout.register_step_two_fragment

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (enteredCode != 0) {
            edit_text_input_code.setText(enteredCode.toString())
        }

        super.onViewStateRestored(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_phone_number.text = RegisterStepOneFragment.enteredPhoneNumber.toString()

        edit_text_input_code.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
                    enteredCode = newText.toString().toInt()
                } catch (error: NumberFormatException) {
                    Log.e("RegisterStepTwoFragment", "Wrong phone number input")
                    edit_text_input_code.error = getString(R.string.error_wrong_number)
                }

                if (enteredCode.toString().length == 4) {
                    registerViewModel.checkCode(enteredCode)
                    activity?.supportFragmentManager?.transaction(allowStateLoss = true) {
                        replace(R.id.main_container, RegisterStepThreeFragment.newInstance())
                        MainActivity.currentRegistrationStep = 3
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    override fun scrollToTop() {

    }
}