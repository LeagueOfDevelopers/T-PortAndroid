package com.lod.rtviwe.tport.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.databinding.FragmentSearchContainerBinding

class SearchContainerFragment : BaseFragment() {

    private lateinit var binding: FragmentSearchContainerBinding

    override fun getLayout() = R.layout.fragment_search_container

    override fun scrollToTop() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchContainerBinding.inflate(inflater, container, false)
        return binding.root
    }
}