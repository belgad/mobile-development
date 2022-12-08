package com.example.lab8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.lab8.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        binding.buttonToTask1.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task1Fragment())
                addToBackStack(null)
            }
        }
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
        binding.buttonToTask4.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task4Fragment())
                addToBackStack(null)
            }
        }
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