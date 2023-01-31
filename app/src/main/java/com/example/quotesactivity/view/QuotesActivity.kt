package com.example.quotesactivity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.quotesactivity.R
import com.example.quotesactivity.di.InjectorUtils
import com.example.quotesactivity.viewModel.QuotesViewModel

class QuotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        initializeUI()
    }

    private fun initializeUI() {
        // Get the QuoteViewModelFactory with all of its dependencies constructed
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        // Use ViewModelProviders class to create / get already created QuotesViewModel for this view (activity)
        val viewModel = ViewModelProviders.of(this, factory)[QuotesViewModel::class.java]

        /**
         * Observing LiveData from the QuotesViewModel which in turn observes LiveData
         * LiveData from the repository, which observes LiveData from the DAO
         */
        viewModel.getQuotes().observe(this, Observer {
            val stringBuilder = StringBuilder()
            it.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
        })



    }
}