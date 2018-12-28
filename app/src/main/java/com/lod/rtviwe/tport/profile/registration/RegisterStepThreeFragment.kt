package com.lod.rtviwe.tport.profile.registration

import android.content.Context
import android.os.Bundle
import android.view.View
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.listeners.RegisterStepThreeListener
import kotlinx.android.synthetic.main.register_step_three_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepThreeFragment : BaseFragment() {

    companion object {

        fun newInstance(): RegisterStepThreeFragment {
            return RegisterStepThreeFragment()
        }
    }

    private val registerViewModel by sharedViewModel<RegisterViewModel>()
    private lateinit var listenerStepThree: RegisterStepThreeListener

    override fun getLayout() = R.layout.register_step_three_fragment

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is RegisterStepThreeListener -> listenerStepThree = context
            else -> throw ClassCastException("$context does not implements RegisterStepThreeListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_register_step_three_continue.setOnClickListener {
            setupNextStep()
        }
    }

    private fun setupNextStep() {
        listenerStepThree.onRegisterStepThreeContinue()
    }
}