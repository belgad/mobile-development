package com.example.lab7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab7.databinding.FragmentCounterBinding

class CounterFragment(counter: Int = 0) : Fragment() {
    private lateinit var binding: FragmentCounterBinding
    var counter: Int = counter
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCounterBinding.inflate(inflater)

        binding.counterTextView.text = counter.toString()
        binding.counterButtonAdd.setOnClickListener {
            ++counter
            binding.counterTextView.text = counter.toString()
        }
        binding.counterButtonReset.setOnClickListener {
            counter = 0
            binding.counterTextView.text = counter.toString()
        }

        return binding.root
    }
}