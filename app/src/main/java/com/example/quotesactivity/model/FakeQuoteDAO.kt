package com.example.quotesactivity.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeQuoteDAO {
    // A Fake Database table
    private val quoteList = mutableListOf<Quote>()
    // Mutable live data is from the architecture components library
    // LiveData can be observed for changes
    private val quoteLiveDataMutable = MutableLiveData<List<Quote>>()
    // we doing this below to prevent the mutable live data to be changed by other classes
    val quoteLiveData: LiveData<List<Quote>>
    get() = quoteLiveDataMutable

    init {
        // immediately connect the now empty quoteList
        quoteLiveDataMutable.value = quoteList
    }

    fun addQuote(quote: Quote) {
        quoteList.add(quote)
        // after adding the quote to the "database" (quoteList)
        // update the value of the MutableLiveData
        quoteLiveDataMutable.value = quoteList
    }

    fun getQuote() = quoteLiveData
}