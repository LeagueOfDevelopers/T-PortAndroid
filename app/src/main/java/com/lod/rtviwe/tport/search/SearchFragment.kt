package com.lod.rtviwe.tport.search

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.search.searchbox.SearchBox
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_box_item.*
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class SearchFragment : BaseFragment() {

    private val searchViewModel by sharedViewModel<SearchViewModel>()

    private val searchAdapter = GroupAdapter<ViewHolder>()
    private val searchBox = SearchBox("", "", "")

    private lateinit var searchListener: SearchListener
    private lateinit var searchLayoutManager: LinearLayoutManager
    private lateinit var searchRecyclerView: RecyclerView

    override fun getLayout() = R.layout.search_fragment

    override fun scrollToTop() {
        scroll_view_search_fragment.smoothScrollTo(0, 0)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is SearchListener -> searchListener = context
            else -> throw ClassCastException("$context does not implement SearchListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.populateAdapter(this, searchAdapter)

        searchLayoutManager = LinearLayoutManager(context)

        searchRecyclerView = recycler_view_search.apply {
            adapter = searchAdapter
            layoutManager = searchLayoutManager
        }

        val autocompleteFromPlaceCallback = { words: List<String> ->
            context?.let {
                autocomplete_text_from_place.setAdapter(
                    ArrayAdapter(it, android.R.layout.simple_dropdown_item_1line, words)
                )
            }
        }

        val autocompleteToPlaceCallback = { words: List<String> ->
            context?.let {
                autocomplete_text_to_place.setAdapter(
                    ArrayAdapter(it, android.R.layout.simple_dropdown_item_1line, words)
                )
            }
        }

        autocomplete_text_from_place.setText(searchBox.fromPlace)
        autocomplete_text_to_place.setText(searchBox.toPlace)
        edit_text_data_travel.setText(searchBox.travelTime)

        autocomplete_text_from_place.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = newText.toString()
                searchBox.fromPlace = text
                searchViewModel.findAutocomplete(text, autocompleteFromPlaceCallback)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}
        })

        autocomplete_text_to_place.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = newText.toString()
                searchBox.toPlace = text
                searchViewModel.findAutocomplete(text, autocompleteToPlaceCallback)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}
        })

        edit_text_data_travel.setOnClickListener {
            callDateTimePicker()
        }

        button_pick_up.setOnClickListener {
            searchListener.openSearchTrips(
                autocomplete_text_from_place.text.toString(),
                autocomplete_text_to_place.text.toString(),
                edit_text_data_travel.text.toString()
            )
        }

        autocomplete_text_from_place.setOnFocusChangeListener { textView, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(textView)
            }
        }

        autocomplete_text_to_place.setOnFocusChangeListener { textView, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(textView)
            }
        }
    }

    private fun callDateTimePicker() {
        val calendar = GregorianCalendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        context?.let {
            DatePickerDialog(
                it,
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    val editTextDateParam = "$selectedDay.${selectedMonth + 1}.$selectedYear"
                    edit_text_data_travel.setText(editTextDateParam)
                    searchBox.travelTime = edit_text_data_travel.text.toString()
                }, year, month, day
            ).show()
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}