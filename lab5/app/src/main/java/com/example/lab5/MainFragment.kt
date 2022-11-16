package com.example.lab5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.lab5.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)

        binding.toTask1Button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task1Fragment())
                addToBackStack(null)
            }
        }

        binding.toTask2Button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task2Fragment())
                addToBackStack(null)
            }
        }

        binding.toTask3Button.visibility = View.GONE
        binding.toTask3Button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task3Fragment())
                addToBackStack(null)
            }
        }

        binding.toTask4Button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task4Fragment())
                addToBackStack(null)
            }
        }

        binding.toTask5Button.visibility = View.GONE
        binding.toTask5Button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task5Fragment())
                addToBackStack(null)
            }
        }

        binding.toTask6Button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task6Fragment())
                addToBackStack(null)
            }
        }

        binding.toTask7Button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task7Fragment())
                addToBackStack(null)
            }
        }

        binding.toTask8Button.visibility = View.GONE
        binding.toTask8Button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, Task8Fragment())
                addToBackStack(null)
            }
        }

        return binding.root
    }
}