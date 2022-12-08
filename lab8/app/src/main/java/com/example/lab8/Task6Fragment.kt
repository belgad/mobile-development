package com.example.lab8

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.example.lab8.databinding.FragmentTask6Binding

class Task6Fragment : Fragment() {
    private lateinit var binding: FragmentTask6Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask6Binding.inflate(inflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        with(binding.task6VideoView) {
            setVideoURI(Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.bunny))
            setMediaController(MediaController(requireContext(), false).apply { setAnchorView(this@with) })
            requestFocus(0)
            start()
        }
    }
}