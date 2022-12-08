package com.example.lab7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.lab7.databinding.FragmentTask4Binding

class Task4Fragment : Fragment() {
    private lateinit var binding: FragmentTask4Binding
    private lateinit var counterFragment: CounterFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask4Binding.inflate(inflater)

        binding.task4Button.setOnClickListener {
            try { binding.task4EditText.text.toString().toInt() }
            catch(_: NumberFormatException) { 0 }
                .also { counterFragment = CounterFragment(it) }
            parentFragmentManager.commit {
                replace(binding.task4FragmentContainer.id, counterFragment)
            }
        }

        return binding.root
    }
}