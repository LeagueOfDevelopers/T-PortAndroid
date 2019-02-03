package com.lod.rtviwe.tport.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lod.rtviwe.tport.R

class DatePickerBottomSheetDialog : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.date_picker_bottom_sheet_dialog, container, false)
    }

    companion object {

        const val TAG = "DATE_PICKER_BOTTOM_SHEET_DIALOG"
    }
}