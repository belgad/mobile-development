package com.example.lab7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.lab7.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        binding.buttonToTask3.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task3Fragment())
                addToBackStack(null)
            }
        }
        binding.buttonToTask4.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task4Fragment())
                addToBackStack(null)
            }
        }
        binding.buttonToTask7.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task7Fragment())
                addToBackStack(null)
            }
        }

        return binding.root
    }
}