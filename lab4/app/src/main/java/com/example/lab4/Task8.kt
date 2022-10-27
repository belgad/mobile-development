package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Task8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task8_activity)

        val task_name: Array<String> = arrayOf("Task 1", "Task 2", "Task 3", "Task 4", "Task 5")
        val task_description: Array<String> =
            arrayOf("Task 1",
                "Task 2",
                "Task 3",
                "Task 4",
                "Task 5\nLorem Ipsum")

        val slideshow_adapter =
            SlideshowAdapter(supportFragmentManager,
                lifecycle,
                task_name,
                task_description)
        val viewpager = findViewById<ViewPager2>(R.id.task8_viewpager)
        viewpager.adapter = slideshow_adapter

        val tablayout = findViewById<TabLayout>(R.id.task8_tablayout)
        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            tab.text = task_name[position]
        }.attach()
    }
}

class SlideshowAdapter(fm: FragmentManager,
                       lc: Lifecycle,
                       private val task_name: Array<String>,
                       private val task_description: Array<String>) :
    FragmentStateAdapter(fm, lc) {
    override fun getItemCount(): Int {
        return task_description.size
    }

    override fun createFragment(position: Int): Fragment {
        val args = Bundle()
        args.putString("task", task_description[position])
        val fragment = SlideshowFragment()
        fragment.arguments = args
        return fragment
    }
}

class SlideshowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task8_fragment, container, false)

        if (arguments != null) {
            view.findViewById<TextView>(R.id.task8_fragment_textview).text =
                requireArguments().getString("task")
        }

        return view
    }
}