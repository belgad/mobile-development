package com.example.lab8

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab8.databinding.FragmentTask7Binding

class Task7Fragment : Fragment() {
    private lateinit var binding: FragmentTask7Binding
    private lateinit var mediaPlayer: MediaPlayer
    private var nowPlaying: Int = 0
    private val resIds: Array<Int> = arrayOf(
        R.raw.blues, R.raw.classical, R.raw.country, R.raw.disco, R.raw.hiphop,
        R.raw.jazz, R.raw.metal, R.raw.pop, R.raw.reggae, R.raw.rock
    )
    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask7Binding.inflate(inflater)

        handler = Handler.createAsync(requireActivity().mainLooper)
        mediaPlayer = MediaPlayer.create(requireContext(), resIds[nowPlaying]).apply {
            isLooping = false
        }
        binding.task7SeekBar.max = mediaPlayer.duration / 1000
        binding.task7TextViewSongName.text = TypedValue().also {
            resources.getValue(resIds[nowPlaying], it, true)
        }.string.substring(8)
        binding.task7TextViewSongDuration.text = (mediaPlayer.duration / 1000).let { String.format("%02d:%02d", it / 60, it % 60) }

        binding.task7ButtonPlayPause.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                binding.task7ButtonPlayPause.text = "Play"
            } else {
                mediaPlayer.start()
                requireActivity().runOnUiThread{
                    handler.postDelayed(object : Runnable {
                    override fun run() {
                        val progress = mediaPlayer.currentPosition / 1000
                        binding.task7SeekBar.progress = progress
                        binding.task7TextViewSongNow.text =
                            String.format("%02d:%02d", progress / 60, progress % 60)
                        handler.postDelayed(this, 500)
                    }
                }, 500)}
                binding.task7ButtonPlayPause.text = "Pause"
            }
        }
        binding.task7ButtonStop.setOnClickListener {
            handler.removeCallbacksAndMessages(null)
            mediaPlayer.apply { stop(); prepare() }
            binding.task7ButtonPlayPause.text = "Play"
            binding.task7SeekBar.progress = 0
            binding.task7TextViewSongNow.text = "00:00"
        }
        binding.task7ButtonNext.setOnClickListener {
            binding.task7ButtonStop.callOnClick()
            nowPlaying = (nowPlaying + 1) % resIds.size
            mediaPlayer = MediaPlayer.create(requireContext(), resIds[nowPlaying]).apply {
                isLooping = false
            }
            binding.task7TextViewSongName.text = TypedValue().also {
                resources.getValue(resIds[nowPlaying], it, true)
            }.string.substring(8)
            binding.task7TextViewSongDuration.text = (mediaPlayer.duration / 1000).let { String.format("%02d:%02d", it / 60, it % 60) }
        }
        binding.task7ButtonPrevious.setOnClickListener {
            binding.task7ButtonStop.callOnClick()
            nowPlaying = (nowPlaying - 1 + resIds.size) % resIds.size
            mediaPlayer = MediaPlayer.create(requireContext(), resIds[nowPlaying]).apply {
                isLooping = false
            }
            binding.task7TextViewSongName.text = TypedValue().also {
                resources.getValue(resIds[nowPlaying], it, true)
            }.string.substring(8)
            binding.task7TextViewSongDuration.text = (mediaPlayer.duration / 1000).let { String.format("%02d:%02d", it / 60, it % 60) }
        }

        return binding.root
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        mediaPlayer.stop()
        mediaPlayer.release()
        super.onDestroy()
    }
}