package com.lod.rtviwe.tport.profile.registration

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import kotlinx.android.synthetic.main.register_step_three_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepThreeFragment : BaseFragment() {

    private val registerViewModel by sharedViewModel<RegisterViewModel>()
    private lateinit var listenerStepThree: RegisterStepThreeListener

    private var phoneNumber: String? = null

    override fun getLayout() = R.layout.register_step_three_fragment

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is RegisterStepThreeListener -> listenerStepThree = context
            else -> throw ClassCastException("$context does not implement RegisterStepThreeListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.also {
            it.getString(RegisterStepTwoFragment.ARGUMENT_PHONE_NUMBER)?.let { phone -> phoneNumber = phone }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_register_step_three_continue.setOnClickListener {
            setupNextStep()
        }

//        edit_text_enter_name.requestFocus()
//        showKeyboard()
    }

    private fun setupNextStep() {
        phoneNumber?.let {
            registerViewModel.sendName(it, edit_text_enter_name.text.toString())
        }
        listenerStepThree.onRegisterStepThreeContinue()
    }

    private fun showKeyboard() {
        edit_text_enter_name.post {
            activity?.let {
                val imm = it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(edit_text_enter_name, 0)
            }
        }
    }
}