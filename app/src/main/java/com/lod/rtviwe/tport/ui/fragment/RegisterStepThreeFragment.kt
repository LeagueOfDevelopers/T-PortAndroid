package com.lod.rtviwe.tport.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.transaction
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.ui.activity.MainActivity
import com.lod.rtviwe.tport.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.register_step_three_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepThreeFragment : BaseFragment() {

    companion object {

        fun newInstance(): RegisterStepThreeFragment {
            return RegisterStepThreeFragment()
        }
    }

    private val registerViewModel by sharedViewModel<RegisterViewModel>()

    override fun getLayout() = R.layout.register_step_three_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_register_step_three_continue.setOnClickListener {
            setupNextStep()
        }
    }

    override fun scrollToTop() {
        scroll_view_step_three.smoothScrollTo(0, 0)
    }

    private fun setupNextStep() {
        activity?.supportFragmentManager?.transaction(allowStateLoss = true) {
            replace(R.id.main_container, ProfileFragment.newInstance())
            MainActivity.currentRegistrationStep = 0
        }
    }
}