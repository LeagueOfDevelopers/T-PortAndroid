package com.lod.rtviwe.tport.profile.registration.stepthree

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.profile.registration.RegisterViewModel
import com.lod.rtviwe.tport.profile.registration.steptwo.RegisterStepTwoFragment
import kotlinx.android.synthetic.main.fragment_register_step_three.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepThreeFragment : BaseFragment() {

    private val registerViewModel by sharedViewModel<RegisterViewModel>()

    private lateinit var navController: NavController
    private var phoneNumber: String = ""

    override fun getLayout() = R.layout.fragment_register_step_three

    override fun scrollToTop() {
        scroll_view_step_three.smoothScrollTo(0, 0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.also {
            it.getString(RegisterStepTwoFragment.ARGUMENT_PHONE_NUMBER)?.let { phone -> phoneNumber = phone }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            navController = Navigation.findNavController(it, R.id.nav_host_fragment)
        }

        button_register_step_three_continue.setOnClickListener {
            registerViewModel.sendName(phoneNumber, edit_text_enter_name.text.toString())
            setupNextStep()
        }

//        edit_text_enter_name.requestFocus()
//        showKeyboard()
    }

    private fun setupNextStep() {
        registerViewModel.navigateToProfileFragment(navController)
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