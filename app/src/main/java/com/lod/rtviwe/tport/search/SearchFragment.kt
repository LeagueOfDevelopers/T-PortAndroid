package com.lod.rtviwe.tport.search

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.utils.setTextChangedListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.header_item.*
import kotlinx.android.synthetic.main.search_box_item.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class SearchFragment : BaseFragment() {

    private val searchViewModel by sharedViewModel<SearchViewModel>()

    private val searchAdapter = GroupAdapter<ViewHolder>()
    private val searchBox = SearchBox("", "", "")

    private lateinit var searchLayoutManager: LinearLayoutManager
    private lateinit var searchRecyclerView: RecyclerView

    override fun getLayout() = R.layout.fragment_search

    override fun scrollToTop() {
        scroll_view_search_fragment.smoothScrollTo(0, 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.populateAdapter(searchAdapter)

        searchLayoutManager = LinearLayoutManager(context)

        searchRecyclerView = recycler_view_search.apply {
            adapter = searchAdapter
            layoutManager = searchLayoutManager
        }

        autocomplete_text_from_place.setText(searchBox.fromPlace)
        autocomplete_text_to_place.setText(searchBox.toPlace)
        edit_text_data_travel.setText(searchBox.travelTime)
        text_view_header.text = getString(R.string.popular_title_item)

        autocomplete_text_from_place.setTextChangedListener { text ->
            searchBox.fromPlace = text
            searchViewModel.findAutocomplete(text, autocomplete_text_from_place)
        }

        autocomplete_text_to_place.setTextChangedListener { text ->
            searchBox.toPlace = text
            searchViewModel.findAutocomplete(text, autocomplete_text_to_place)
        }

        edit_text_data_travel.setOnClickListener {
            callDateTimePicker()
        }

        button_pick_up.setOnClickListener {
            navigateToSearchTrips()
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

    private fun navigateToSearchTrips() {
        searchViewModel.navigateToSearchTrip(
            findNavController(),
            SearchBox(
                autocomplete_text_from_place.text.toString(),
                autocomplete_text_to_place.text.toString(),
                edit_text_data_travel.text.toString()
            )
        )
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
