package com.lod.rtviwe.tport.search

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.search.items.SearchBox
import com.lod.rtviwe.tport.search.searchtrip.SearchTripsFragment
import com.lod.rtviwe.tport.utils.setTextChangedListener
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

    private lateinit var navController: NavController
    private lateinit var searchLayoutManager: LinearLayoutManager
    private lateinit var searchRecyclerView: RecyclerView

    override fun getLayout() = R.layout.search_fragment

    override fun scrollToTop() {
        scroll_view_search_fragment.smoothScrollTo(0, 0)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        activity?.let {
            navController = Navigation.findNavController(it, R.id.nav_host_fragment)
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

        autocomplete_text_from_place.setText(searchBox.fromPlace)
        autocomplete_text_to_place.setText(searchBox.toPlace)
        edit_text_data_travel.setText(searchBox.travelTime)

        autocomplete_text_from_place.setTextChangedListener { text ->
            searchBox.fromPlace = text
            searchViewModel.findAutocomplete(text) { words ->
                context?.let {
                    autocomplete_text_from_place.setAdapter(
                        ArrayAdapter(it, android.R.layout.simple_dropdown_item_1line, words)
                    )
                }
            }
        }

        autocomplete_text_to_place.setTextChangedListener { text ->
            searchBox.toPlace = text
            searchViewModel.findAutocomplete(text) { words ->
                context?.let {
                    autocomplete_text_to_place.setAdapter(
                        ArrayAdapter(it, android.R.layout.simple_dropdown_item_1line, words)
                    )
                }
            }
        }

        edit_text_data_travel.setOnClickListener {
            callDateTimePicker()
        }

        button_pick_up.setOnClickListener {
            activity?.let {
                navigateToSearchTrip()
            }
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

    private fun navigateToSearchTrip() {
        val bundle = Bundle().apply {
            putString(SearchTripsFragment.ARGUMENT_FROM_PLACE, autocomplete_text_from_place.text.toString())
            putString(SearchTripsFragment.ARGUMENT_TO_PLACE, autocomplete_text_to_place.text.toString())
            putString(SearchTripsFragment.ARGUMENT_TRAVEL_TIME, edit_text_data_travel.text.toString())
        }
        navController.navigate(R.id.action_searchFragment_to_searchTripsFragment, bundle)
    }
}
