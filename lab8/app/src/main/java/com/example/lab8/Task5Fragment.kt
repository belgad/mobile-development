package com.example.lab8

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab8.databinding.FragmentTask5Binding
import kotlin.random.Random

class Task5Fragment : Fragment() {
    private val resIds: Array<Int> = arrayOf(
        R.raw.blues, R.raw.classical, R.raw.country, R.raw.disco, R.raw.hiphop,
        R.raw.jazz, R.raw.metal, R.raw.pop, R.raw.reggae, R.raw.rock
    )
    private val nowPlaying: Int = Random.Default.nextInt(resIds.size)
    private lateinit var binding: FragmentTask5Binding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask5Binding.inflate(inflater)

        handler = Handler.createAsync(requireActivity().mainLooper)
        mediaPlayer = MediaPlayer.create(requireContext(), resIds[nowPlaying]).apply {
            isLooping = false
        }
        binding.task5SeekBar.max = mediaPlayer.duration / 1000
        binding.task5TextViewSongName.text = TypedValue().also {
            resources.getValue(resIds[nowPlaying], it, true)
        }.string.substring(8)
        binding.task5TextViewSongDuration.text = (mediaPlayer.duration / 1000).let { String.format("%02d:%02d", it / 60, it % 60) }

        binding.task5ButtonPlayPause.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                binding.task5ButtonPlayPause.text = "Play"
            } else {
                mediaPlayer.start()
                requireActivity().runOnUiThread{
                    handler.postDelayed(object : Runnable {
                    override fun run() {
                        val progress = mediaPlayer.currentPosition / 1000
                        binding.task5SeekBar.progress = progress
                        binding.task5TextViewSongNow.text =
                            String.format("%02d:%02d", progress / 60, progress % 60)
                        handler.postDelayed(this, 500)
                    }
                }, 500)}
                binding.task5ButtonPlayPause.text = "Pause"
            }
        }
        binding.task5ButtonStop.setOnClickListener {
            handler.removeCallbacksAndMessages(null)
            mediaPlayer.apply { stop(); prepare() }
            binding.task5ButtonPlayPause.text = "Play"
            binding.task5SeekBar.progress = 0
            binding.task5TextViewSongNow.text = "00:00"
        }

        return binding.root
    }

    override fun onDestroyView() {
        handler.removeCallbacksAndMessages(null)
        mediaPlayer.stop()
        mediaPlayer.release()
        super.onDestroyView()
    }
}