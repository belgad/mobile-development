package com.example.lab6

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.lab6.databinding.FragmentTask7Binding

class Task7Fragment : Fragment() {
    private lateinit var binding: FragmentTask7Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask7Binding.inflate(inflater)

        val preferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val numCreated = preferences.getInt("Task7NumCreated", 1)

        binding.task7TvSize.text = "Stack size: ${parentFragmentManager.backStackEntryCount}"
        binding.task7TvCreated.text = "Fragments created: $numCreated"
        binding.task7TvDeleted.text = "Fragments deleted: ${numCreated - parentFragmentManager.backStackEntryCount}"

        binding.task7ButtonNext.setOnClickListener {
            preferences.edit {
                putInt("Task7NumCreated", numCreated + 1)
                commit()
            }
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task7Fragment())
                addToBackStack("Task7")
            }
        }
        binding.task7ButtonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }
}