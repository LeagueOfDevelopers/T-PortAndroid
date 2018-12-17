package com.lod.rtviwe.tport.ui.viewholder.searchfragment

import android.app.Activity
import android.app.DatePickerDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.model.searchfragment.SearchBoxItem
import com.lod.rtviwe.tport.ui.listeners.SearchListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.search_item.*
import java.util.*

class ViewHolderSearchItem(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private lateinit var searchListener: SearchListener

    lateinit var boxItem: SearchBoxItem

    fun bind(searchBoxItem: SearchBoxItem) {
        when (containerView.context) {
            is SearchListener -> searchListener = containerView.context as SearchListener
            else -> throw ClassCastException("${containerView.context} does not implements SearchListener")
        }

        boxItem = searchBoxItem
        edit_text_from_place.setText(boxItem.fromPlace)
        edit_text_to_place.setText(boxItem.toPlace)
        edit_text_data_travel.setText(boxItem.travelTime)

        edit_text_from_place?.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                boxItem.fromPlace = newText.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}
        })

        edit_text_to_place?.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                boxItem.toPlace = newText.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}
        })

        edit_text_data_travel.setOnClickListener {
            callDateTimePicker()
        }

        button_pick_up.setOnClickListener {
            searchListener.onPickUpButton(edit_text_from_place.text.toString(), edit_text_to_place.text.toString())
        }

        image_button_change.setOnClickListener {
            changeDestinations()
        }

        edit_text_from_place.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(view)
            }
        }

        edit_text_to_place.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(view)
            }
        }
    }

    private fun changeDestinations() {
        val temp = edit_text_from_place.text
        edit_text_from_place.text = edit_text_to_place.text
        edit_text_to_place.text = temp
    }

    private fun callDateTimePicker() {
        val calendar = GregorianCalendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            containerView.context,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                val editTextDateParam = "$selectedDay.${selectedMonth + 1}.$selectedYear"
                edit_text_data_travel.setText(editTextDateParam)
                boxItem.travelTime = edit_text_data_travel.text.toString()
            }, year, month, day
        ).show()
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            containerView.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}