package com.lod.rtviwe.tport.ui.viewholder

import android.app.Activity
import android.app.DatePickerDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.model.recyclerview.SearchItem
import com.lod.rtviwe.tport.ui.listeners.SearchListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.search_item.*
import java.util.*

class ViewHolderSearchItem(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private lateinit var searchListener: SearchListener

    lateinit var item: SearchItem

    fun bind(searchItem: SearchItem) {
        when (containerView.context) {
            is SearchListener -> searchListener = containerView.context as SearchListener
            else -> throw ClassCastException("${containerView.context} does not implements SearchListener")
        }

        item = searchItem
        edit_text_from_place.setText(item.fromPlace)
        edit_text_to_place.setText(item.toPlace)
        edit_text_data_travel.setText(item.travelTime)

        edit_text_from_place?.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                item.fromPlace = newText.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}
        })

        edit_text_to_place?.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                item.toPlace = newText.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}
        })

        edit_text_data_travel.setOnClickListener {
            callDateTimePicker()
        }

        button_pick_up.setOnClickListener {
            searchListener.onPickUpButton()
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
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            containerView.context,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                val editTextDateParam = "$selectedDay.${selectedMonth + 1}.$selectedYear"
                edit_text_data_travel.setText(editTextDateParam)
                item.travelTime = edit_text_data_travel.text.toString()
            }, year, month, day
        ).show()
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            containerView.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}