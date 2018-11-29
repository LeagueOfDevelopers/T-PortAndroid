package com.lod.rtviwe.tport.ui.fragment

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment() {

    companion object {

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    private val profileViewModel: ProfileViewModel by viewModel()

    override fun getLayout() = R.layout.profile_fragment

    override fun scrollToTop() {

    }
}