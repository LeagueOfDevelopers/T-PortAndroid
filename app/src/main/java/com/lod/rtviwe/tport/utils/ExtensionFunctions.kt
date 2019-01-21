package com.lod.rtviwe.tport.utils

import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatAutoCompleteTextView

fun String.toPhone() = "+7$this"

fun AppCompatAutoCompleteTextView.setTextChangedListener(function: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {

        override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
            function(newText.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {}
    })
}

