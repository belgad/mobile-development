package com.example.lab7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab7.databinding.FragmentTask7Binding

class Task7Fragment : Fragment() {
    private lateinit var binding: FragmentTask7Binding
    private lateinit var wordList: WordList

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask7Binding.inflate(inflater)

        wordList = WordList()

        binding.task7ButtonAdd.setOnClickListener {
            wordList.add(binding.task7EditText.text.toString().trim())
            binding.task7TextView.text = wordList.getAsString()
            binding.task7EditText.setText("")
        }
        binding.task7ButtonDelete.setOnClickListener {
            wordList.removeLast()
            binding.task7TextView.text = wordList.getAsString()
        }
        binding.task7ButtonSortList.setOnClickListener {
            binding.task7TextView.text = wordList.getAsSortedString()
        }

        return binding.root
    }
}