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

        edit_text_phone_number.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
                    enteredPhoneNumber = newText.toString().toLong()
                } catch (error: NumberFormatException) {
                    Log.e("RegisterStepTwoFragment", "Wrong phone number input")
                    edit_text_phone_number.error = getString(R.string.error_wrong_number)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}
        })

        button_register_step_one_continue.setOnClickListener {
            if (edit_text_phone_number.text.toString().length == 11) {
                activity?.supportFragmentManager?.transaction(allowStateLoss = true) {
                    replace(R.id.main_container, RegisterStepTwoFragment.newInstance())
                    MainActivity.currentRegistrationStep = 2
                    enteredPhoneNumber = edit_text_phone_number.text.toString().toLong()
                    registerViewModel.sendCodeToPhoneNumber(enteredPhoneNumber)
                }
            } else {
                edit_text_phone_number.error = getString(R.string.error_wrong_number)
            }
        }
    }

    override fun scrollToTop() {

    }
}