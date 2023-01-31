package com.example.quotesactivity.di

import com.example.quotesactivity.model.FakeDatabase
import com.example.quotesactivity.model.QuoteRepository
import com.example.quotesactivity.viewModel.QuotesViewModelFactory

// A singleton which does not needs anythign passed in constructor
object InjectorUtils {
    // This will be called from QuotesActivity
    fun provideQuotesViewModelFactory(): QuotesViewModelFactory {
        /**
         * ViewModelFactory needs a repository, which in turn needs a DAO from a database
         * The whole dependency tree is constructed right here in one place
         * */
        val quoteRepository =
            // here below the FakeDatabase singleton Object is created and returns the FakeQuoteDao
            QuoteRepository.getInstance(FakeDatabase.getInstance().quotesDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}