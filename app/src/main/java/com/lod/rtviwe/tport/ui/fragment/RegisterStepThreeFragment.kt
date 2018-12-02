package com.lod.rtviwe.tport.ui.fragment

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepThreeFragment : BaseFragment() {

    companion object {

        fun newInstance(): RegisterStepThreeFragment {
            return RegisterStepThreeFragment()
        }
    }

    private val registerViewModel by sharedViewModel<RegisterViewModel>()

    override fun getLayout() = R.layout.register_step_three_fragment

    override fun scrollToTop() {

    }
}