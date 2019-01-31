package com.lod.rtviwe.tport.search.searchtrips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lod.rtviwe.tport.R

class FilterBottomSheetDialog : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.filter_bottom_sheet, container, false)
    }

    companion object {

        const val TAG = "FILTER_BOTTOM_SHEET_DIALOG"
    }
}