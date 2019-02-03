package com.lod.rtviwe.tport.search

import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.search.DatePickerBottomSheetDialog.Companion.TAG
import com.lod.rtviwe.tport.utils.setTextChangedListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.header_item.*
import kotlinx.android.synthetic.main.search_box_item.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment : BaseFragment() {

    private val searchViewModel by sharedViewModel<SearchViewModel>()

    private val searchAdapter = GroupAdapter<ViewHolder>()
    private val searchBox = SearchBox("", "", "")

    private lateinit var searchLayoutManager: LinearLayoutManager
    private lateinit var searchRecyclerView: RecyclerView
    private lateinit var datePickerBottomSheetDialog: DatePickerBottomSheetDialog

    override fun getLayout() = R.layout.fragment_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_header.setTypeface(text_view_header.typeface, Typeface.BOLD)
        button_pick_up.typeface = Typeface.createFromAsset(context!!.assets, "lucidagrande.ttf")

        searchViewModel.populateAdapter(searchAdapter)

        searchLayoutManager = LinearLayoutManager(context)

        searchRecyclerView = recycler_view_search.apply {
            adapter = searchAdapter
            layoutManager = searchLayoutManager
        }

        datePickerBottomSheetDialog = DatePickerBottomSheetDialog()

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

        image_button_change.setOnClickListener {
            swapDestinations()
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
        datePickerBottomSheetDialog.show(activity!!.supportFragmentManager, TAG)
    }

    private fun swapDestinations() {
        val temp = autocomplete_text_from_place.text
        autocomplete_text_from_place.text = autocomplete_text_to_place.text
        autocomplete_text_to_place.text = temp
    }


    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
