package com.lod.rtviwe.tport.profile

import android.os.Bundle
import android.view.View
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import kotlinx.android.synthetic.main.profile_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment() {

    companion object {

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    private val profileViewModel: ProfileViewModel by viewModel()

    override fun getLayout() = R.layout.profile_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_profile_name.text = getUserName()
    }

    private fun getUserName() = "Сергей Иванов"
}