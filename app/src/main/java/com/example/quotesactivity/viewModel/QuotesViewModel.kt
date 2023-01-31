package com.example.quotesactivity.viewModel

import androidx.lifecycle.ViewModel
import com.example.quotesactivity.model.Quote
import com.example.quotesactivity.model.QuoteRepository

class QuotesViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {
    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)
}