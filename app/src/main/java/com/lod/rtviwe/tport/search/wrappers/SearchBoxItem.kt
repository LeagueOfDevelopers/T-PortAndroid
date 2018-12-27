package com.lod.rtviwe.tport.search.wrappers

import android.app.Activity
import android.app.DatePickerDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.listeners.SearchListener
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_box_item.*
import java.util.*

class SearchBoxItem(private val searchBox: SearchBox) : Item() {

    private lateinit var searchListener: SearchListener

    override fun getLayout() = R.layout.search_box_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        when (viewHolder.containerView.context) {
            is SearchListener -> searchListener =
                    viewHolder.containerView.context as SearchListener
            else -> throw ClassCastException("${viewHolder.containerView.context} does not implements SearchListener")
        }

        viewHolder.edit_text_from_place.setText(searchBox.fromPlace)
        viewHolder.edit_text_to_place.setText(searchBox.toPlace)
        viewHolder.edit_text_data_travel.setText(searchBox.travelTime)

        viewHolder.edit_text_from_place?.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchBox.fromPlace = newText.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}
        })

        viewHolder.edit_text_to_place?.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchBox.toPlace = newText.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}
        })

        viewHolder.edit_text_data_travel.setOnClickListener {
            callDateTimePicker(viewHolder)
        }

        viewHolder.button_pick_up.setOnClickListener {
            searchListener.onPickUpButton(
                viewHolder.edit_text_from_place.text.toString(),
                viewHolder.edit_text_to_place.text.toString(),
                viewHolder.edit_text_data_travel.text.toString()
            )
        }

//        viewHolder.image_button_change.setOnClickListener {
//            swapDestinations()
//        }

        viewHolder.edit_text_from_place.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(view, viewHolder)
            }
        }

        viewHolder.edit_text_to_place.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(view, viewHolder)
            }
        }
    }

//    private fun swapDestinations(viewHolder: ViewHolder) {
//        val temp = viewHolder.edit_text_from_place.text
//        viewHolder.edit_text_from_place.text = viewHolder.edit_text_to_place.text
//        viewHolder.edit_text_to_place.text = temp
//    }

    private fun callDateTimePicker(viewHolder: ViewHolder) {
        val calendar = GregorianCalendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            viewHolder.containerView.context,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                val editTextDateParam = "$selectedDay.${selectedMonth + 1}.$selectedYear"
                viewHolder.edit_text_data_travel.setText(editTextDateParam)
                searchBox.travelTime = viewHolder.edit_text_data_travel.text.toString()
            }, year, month, day
        ).show()
    }

    private fun hideKeyboard(view: View, viewHolder: ViewHolder) {
        val inputMethodManager =
            viewHolder.containerView.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}