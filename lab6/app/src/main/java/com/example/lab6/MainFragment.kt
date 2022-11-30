package com.example.lab6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.lab6.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        binding.buttonToTask1.visibility = View.GONE
        binding.buttonToTask2.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task2Fragment())
                addToBackStack(null)
            }
        }
        binding.buttonToTask3.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task3Fragment())
                addToBackStack(null)
            }
        }
        binding.buttonToTask4.visibility = View.GONE
        binding.buttonToTask5.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task5Fragment())
                addToBackStack(null)
            }
        }
        binding.buttonToTask6.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task6Fragment())
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