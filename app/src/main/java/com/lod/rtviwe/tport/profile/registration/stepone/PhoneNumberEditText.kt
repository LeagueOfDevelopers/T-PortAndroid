package com.lod.rtviwe.tport.profile.registration.stepone

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputEditText
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.utils.CountryUtils
import com.redmadrobot.inputmask.MaskedTextChangedListener
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class PhoneNumberEditText : TextInputEditText, KoinComponent {

    private val countryUtils: CountryUtils by inject()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle) {
        init()
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newText = s.toString()

                var flagImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_empty_flag, null)

                countryUtils.findCountry(newText)?.let {
                    val flagResource = countryUtils.findFragResource(it)
                    flagImage = ResourcesCompat.getDrawable(resources, flagResource, null)
                }

                setCompoundDrawablesRelativeWithIntrinsicBounds(flagImage, null, null, null)
            }

            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        hint = MaskedTextChangedListener.installOn(
            this,
            "+[0] ([000]) [000] [00] [00]",
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(maskFilled: Boolean, extractedValue: String) {

                }
            }).placeholder()
    }
}
