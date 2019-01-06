package com.lod.rtviwe.tport.search.items

import android.app.Activity
import android.app.DatePickerDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.search.SearchListener
import com.lod.rtviwe.tport.search.SearchViewModel
import com.lod.rtviwe.tport.search.searchbox.AutocompleteCallback
import com.lod.rtviwe.tport.search.searchbox.SearchBox
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_box_item.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import java.util.*

class SearchBoxItem(private val searchBox: SearchBox) : Item(), KoinComponent {

    private lateinit var searchListener: SearchListener

    private val searchViewModel: SearchViewModel = get()

    override fun getLayout() = R.layout.search_box_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        when (viewHolder.containerView.context) {
            is SearchListener -> searchListener =
                    viewHolder.containerView.context as SearchListener
            else -> throw ClassCastException("${viewHolder.containerView.context} does not implement SearchListener")
        }

        val autocompleteFromPlaceCallback = object : AutocompleteCallback {

            override fun autocomplete(words: List<String>) {
                viewHolder.autocomplete_text_from_place.setAdapter(
                    ArrayAdapter(
                        viewHolder.containerView.context,
                        android.R.layout.simple_dropdown_item_1line,
                        words
                    )
                )
            }
        }

        val autocompleteToPlaceCallback = object : AutocompleteCallback {

            override fun autocomplete(words: List<String>) {
                viewHolder.autocomplete_text_to_place.setAdapter(
                    ArrayAdapter(
                        viewHolder.containerView.context,
                        android.R.layout.simple_dropdown_item_1line,
                        words
                    )
                )
            }
        }

        viewHolder.autocomplete_text_from_place.setText(searchBox.fromPlace)
        viewHolder.autocomplete_text_to_place.setText(searchBox.toPlace)
        viewHolder.edit_text_data_travel.setText(searchBox.travelTime)

        viewHolder.autocomplete_text_from_place?.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = newText.toString()
                searchBox.fromPlace = text
                searchViewModel.findAutocomplete(text, autocompleteFromPlaceCallback)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}
        })

        viewHolder.autocomplete_text_to_place?.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = newText.toString()
                searchBox.toPlace = text
                searchViewModel.findAutocomplete(text, autocompleteToPlaceCallback)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}
        })

        viewHolder.edit_text_data_travel.setOnClickListener {
            callDateTimePicker(viewHolder)
        }

        viewHolder.button_pick_up.setOnClickListener {
            searchListener.onPickUpButton(
                viewHolder.autocomplete_text_from_place.text.toString(),
                viewHolder.autocomplete_text_to_place.text.toString(),
                viewHolder.edit_text_data_travel.text.toString()
            )
        }

        viewHolder.autocomplete_text_from_place.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(view, viewHolder)
            }
        }

        viewHolder.autocomplete_text_to_place.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(view, viewHolder)
            }
        }
    }

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