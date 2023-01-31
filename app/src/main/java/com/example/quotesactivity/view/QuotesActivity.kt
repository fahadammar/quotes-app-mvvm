package com.example.quotesactivity.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quotesactivity.databinding.ActivityQuotesBinding
import com.example.quotesactivity.di.InjectorUtils
import com.example.quotesactivity.model.Quote
import com.example.quotesactivity.viewModel.QuotesViewModel

class QuotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUI(binding)
    }

    private fun initializeUI(viewBinding: ActivityQuotesBinding) {
        // Get the QuoteViewModelFactory with all of its dependencies constructed
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        // Use ViewModelProviders class to create / get already created QuotesViewModel for this view (activity)
        val viewModel = ViewModelProviders.of(this, factory)[QuotesViewModel::class.java]

        /**
         * Observing LiveData from the QuotesViewModel which in turn observes LiveData
         * LiveData from the repository, which observes LiveData from the DAO
         */
        viewModel.getQuotes().observe(this, Observer { quotesList ->
            val stringBuilder = StringBuilder()
            quotesList.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            viewBinding.textViewQuotes.text = stringBuilder.toString()
        })

        // When button is clicked, instantiate a Quote and add it to DB through the ViewModel
        viewBinding.buttonAddQuote.setOnClickListener {
            // Quote Instance creation
            val quote = Quote(
                viewBinding.editTextQuote.text.toString(),
                viewBinding.editTextAuthor.text.toString()
            )

            viewModel.addQuote(quote)

            viewBinding.editTextQuote.setText("")
            viewBinding.editTextAuthor.setText("")
        }
    }
}