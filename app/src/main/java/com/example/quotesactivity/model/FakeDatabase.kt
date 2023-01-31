package com.example.quotesactivity.model

// here we are making the singleton the Java way in Kotlin
// private primary constructor inaccessible from other classes
class FakeDatabase private constructor() {

    // All th DAOs go here
    var quotesDao = FakeQuoteDAO()
        private set

    companion object {
        //@Volatile - Writes to this property are immediately visible to other threads
        @Volatile
        private var instance: FakeDatabase? = null

        // The only way to get hold of the FakeDatabase object - getInstance returns the instance of FakeDatabase
        fun getInstance() =
            // Already instantiated? - return the instance
            // Otherwise instantiate in a thread-safe manner
            instance ?: synchronized(this) {
                // if it's still not instantiated, finally create an object
                // also set the "instance" property to be the current created one
                instance ?: FakeDatabase().also { instance = it }
            }
    }

}