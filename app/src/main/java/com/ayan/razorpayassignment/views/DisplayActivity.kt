package com.ayan.razorpayassignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ayan.razorpayassignment.databinding.ActivityDisplayBinding
import com.ayan.razorpayassignment.getLabel
import com.ayan.razorpayassignment.getTextView
import com.ayan.razorpayassignment.models.DisplayData

class DisplayActivity : AppCompatActivity() {

    private val TAG = "DisplayActivity"

    private lateinit var binding: ActivityDisplayBinding
    private var displayList: ArrayList<DisplayData>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Display Input"
        displayList = intent.getParcelableArrayListExtra<DisplayData>(MainActivity.EXTRA_DISPLAY_DATA)

        Log.d(TAG, displayList.toString())

        displayData()


    }

    private fun displayData(){
        binding.parent.removeAllViews()
        displayList?.forEach {

            it.label?.let {
                val label = getLabel(it, it)
                binding.parent.addView(label)
            }
            it.value?.let {
                val label = getTextView(it, it)
                binding.parent.addView(label)
            }
        }
    }
}