package com.example.quotesactivity.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quotesactivity.model.QuoteRepository

// The same repository that's needed for QuotesViewModel
class QuotesViewModelFactory(private val quoteRepository: QuoteRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuotesViewModel(quoteRepository) as T
    }
}