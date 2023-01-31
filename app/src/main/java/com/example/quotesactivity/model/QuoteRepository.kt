package com.example.quotesactivity.model

/*
* FakeQuoteDao must be passed in - it is a dependency
* you could also instantiate the DAO right inside the class without all the fuss, right?
* No!! this would break testability - you need to be able to pass a mock version of a DAO
* to the repository. (i.e one that upon calling getQuotes() returns a dummy list of quotes for testing)
* This is the core idea behind DEPENDENCY INJECTION. - making this comppletely modular and independent
* */

// We are also gonna make the singleton the Java way in Kotlin
class QuoteRepository private constructor(private val fakeQuoteDao: FakeQuoteDAO) {

    fun addQuote(quote: Quote) {
        fakeQuoteDao.addQuote(quote)
    }

    fun getQuotes() = fakeQuoteDao.getQuote()

    companion object {
        //Singleton instantiation you already know and love
        @Volatile
        private var instance: QuoteRepository? = null

        fun getInstance(quoteDao : FakeQuoteDAO) =
            instance ?: synchronized(this) {
                instance ?: QuoteRepository(FakeQuoteDAO()).also { instance = it }
            }
    }
}