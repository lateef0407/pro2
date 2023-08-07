package com.example.pro2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var segmentFragment: SegmentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the SegmentFragment or create a new instance if it doesn't exist
        segmentFragment = supportFragmentManager.findFragmentByTag("segment_fragment") as? SegmentFragment
            ?: SegmentFragment()

        // Add the SegmentFragment to the container if it doesn't exist
        if (!segmentFragment.isAdded) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, segmentFragment, "segment_fragment")
            fragmentTransaction.commit()
        }

        // Set click listener for the "Start Animation" button
        val btnStartAnimation = findViewById<Button>(R.id.btnStartAnimation)
        btnStartAnimation.setOnClickListener {
            segmentFragment.startAnimations()
            setInitialBackgroundColor()
        }

        // Set click listener for the "Stop Animation" button
        val btnStopAnimation = findViewById<Button>(R.id.btnStopAnimation)
        btnStopAnimation.setOnClickListener {
            segmentFragment.stopAnimations()
        }
    }

    private fun setInitialBackgroundColor() {
        segmentFragment.updateBackgroundColor(segmentFragment.backgroundColors[0])
    }
}
