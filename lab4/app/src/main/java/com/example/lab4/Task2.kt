package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import java.util.Random

class Task2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task2_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.task2_fragment_container, Task2Fragment1())
            }
        }
    }
}

class Task2Fragment1 : Fragment() {
    private var f: Fragment? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.task2_fragment1, container, false)

        view.findViewById<Button>(R.id.task2_button1).setOnClickListener {
            if (f == null) {
                f = Task2Fragment2()
            }
            parentFragmentManager.commit {
                replace(R.id.task2_fragment_container, f!!)
                addToBackStack(null)
            }
        }

        view.findViewById<Button>(R.id.task2_button2).setOnClickListener {
            if (f != null) {
                parentFragmentManager.commit {
                    remove(f!!)
                }
                f = null
            }
        }

        return view
    }
}

class Task2Fragment2 : Fragment() {
    private var rnd: Int? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.task2_fragment2, container, false)

        if (rnd == null) {
            rnd = Random().nextInt()
        }
        view.findViewById<TextView>(R.id.task2_rnd).text = rnd!!.toString()

        view.findViewById<Button>(R.id.task2_button3).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}