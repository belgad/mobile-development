package com.example.lab7

class WordList {
    private val data: ArrayList<String> = arrayListOf()
    val size: Int = data.size

    fun add(word: String) {
        if (word.isNotEmpty()) data.add(word.lowercase())
    }

    fun removeLast() {
        data.removeLastOrNull()
    }

    fun getAsString(): String = data.joinToString().replaceFirstChar { it.uppercaseChar() }
    fun getAsSortedString(): String = data.sorted().joinToString().replaceFirstChar { it.uppercaseChar() }
}