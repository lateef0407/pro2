//package com.example.pro2
//
//import android.animation.Animator
//import android.animation.AnimatorSet
//import android.animation.ObjectAnimator
//import android.media.MediaPlayer
//import android.os.Bundle
//import android.os.Handler
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.animation.Animation
//import android.view.animation.AnimationUtils
//import android.widget.ImageView
//import androidx.fragment.app.Fragment
//import android.view.animation.LinearInterpolator
//
//
//
//class SegmentFragment : Fragment() {
//    // MediaPlayer instance
//    private lateinit var mediaPlayer: MediaPlayer
//
//    // Animation used for scaling
//    private lateinit var animation: Animation
//
////    override fun onCreateView(
////        inflater: LayoutInflater, container: ViewGroup?,
////        savedInstanceState: Bundle?
////    ): View? {
////        val view = inflater.inflate(R.layout.fragment_segment, container, false)
////
////        // Initialize the MediaPlayer with the music file
////        mediaPlayer = MediaPlayer.create(context, R.raw.summer_song)
////
////        // Get references to the ImageViews
////        val segment1 = view.findViewById<ImageView>(R.id.segment1)
////        val segment2 = view.findViewById<ImageView>(R.id.segment2)
////        val segment3 = view.findViewById<ImageView>(R.id.segment3)
////
////        // Set click listener for all segments
////        segment1.setOnClickListener {
////            // Play music when any segment is clicked
////            mediaPlayer.start()
////        }
////
////        segment2.setOnClickListener {
////            mediaPlayer.start()
////        }
////
////        segment3.setOnClickListener {
////            mediaPlayer.start()
////        }
////
////        // Set the animation
////        animation = AnimationUtils.loadAnimation(context, R.anim.move_n_repeat)
////
////        // Get reference to the new ImageView
////        val newImageView = view?.findViewById<ImageView>(R.id.newImageView)
////
////        // Apply fade-in animation
////        val fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
////        newImageView?.startAnimation(fadeInAnimation)
////
////
////        // Return the inflated view
////        return view
////    }
////
////    // Function to apply animations to all ImageViews simultaneously
////    private fun applyAnimations(vararg imageViews: ImageView) {
////        val animators = mutableListOf<Animator>()
////
////        for (imageView in imageViews) {
////            imageView.startAnimation(animation)
////            val scaleXAnimator = ObjectAnimator.ofFloat(imageView, View.SCALE_X, 1.0f, 1.2f, 1.0f)
////            val scaleYAnimator = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, 1.0f, 1.2f, 1.0f)
////            animators.add(scaleXAnimator)
////            animators.add(scaleYAnimator)
////        }
////
////        // Add rotation animation for wheeloftime ImageView
////        val wheelOfTime = view?.findViewById<ImageView>(R.id.wheeloftime)
////        wheelOfTime?.let {
////            val rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate)
////            it.startAnimation(rotateAnimation)
////        }
////
////        val animatorSet = AnimatorSet()
////        animatorSet.playTogether(animators)
////        animatorSet.start()
////    }
////
////
////    // Function to start animations for all ImageViews simultaneously
////    fun startAnimations() {
////        val segment1 = view?.findViewById<ImageView>(R.id.segment1)
////        val segment2 = view?.findViewById<ImageView>(R.id.segment2)
////        val segment3 = view?.findViewById<ImageView>(R.id.segment3)
////
////        applyAnimations(segment1!!, segment2!!, segment3!!)
////    }
////
////    // Function to stop animations for all ImageViews
////    fun stopAnimations() {
////        val segment1 = view?.findViewById<ImageView>(R.id.segment1)
////        val segment2 = view?.findViewById<ImageView>(R.id.segment2)
////        val segment3 = view?.findViewById<ImageView>(R.id.segment3)
////
////        // Cancel the animation for each ImageView
////        segment1?.animation?.cancel()
////        segment2?.animation?.cancel()
////        segment3?.animation?.cancel()
////    }
////
////
////    override fun onDestroyView() {
////        super.onDestroyView()
////        // Release the MediaPlayer when the fragment's view is destroyed
////        mediaPlayer.release()
////    }
////}
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_segment, container, false)
//
//        // Initialize the MediaPlayer with the music file
//        mediaPlayer = MediaPlayer.create(context, R.raw.summer_song)
//
//        // Get references to the ImageViews
//        val segment1 = view.findViewById<ImageView>(R.id.segment1)
//        val segment2 = view.findViewById<ImageView>(R.id.segment2)
//        val segment3 = view.findViewById<ImageView>(R.id.segment3)
//
//        // Set click listener for all segments
//        segment1.setOnClickListener {
//            // Play music when any segment is clicked
//            mediaPlayer.start()
//        }
//
//        segment2.setOnClickListener {
//            mediaPlayer.start()
//        }
//
//        segment3.setOnClickListener {
//            mediaPlayer.start()
//        }
//
//        // Set the animation
//        animation = AnimationUtils.loadAnimation(context, R.anim.move_n_repeat)
//
//        // Get reference to the new ImageView
//        val newImageView = view.findViewById<ImageView>(R.id.newImageView)
//
//        // Set up a Handler to change the image periodically (slideshow effect)
//        val imageResources = intArrayOf(
//            R.drawable.autumn,
//            R.drawable.cloud_1,
//            R.drawable.sun3f
//        )
//
//        val handler = Handler()
//        var imageIndex = 0
//        val delayInMillis = 3000 // 3 seconds delay between image changes
//
//        val runnable = object : Runnable {
//            override fun run() {
//                // Set the next image resource in the array
//                newImageView.setImageResource(imageResources[imageIndex])
//
//                // Increment the image index, and loop back to the first image if needed
//                imageIndex = (imageIndex + 1) % imageResources.size
//
//                // Schedule the next image change after the delay
//                handler.postDelayed(this, delayInMillis.toLong())
//            }
//        }
//
//        // Start the slideshow
//        handler.postDelayed(runnable, delayInMillis.toLong())
//
//        // Return the inflated view
//        return view
//    }
//}
package com.example.pro2

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment

class SegmentFragment : Fragment() {
    // MediaPlayer instance
    private lateinit var mediaPlayer: MediaPlayer

    // Animation used for scaling
    private lateinit var animation: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_segment, container, false)

        // Initialize the MediaPlayer with the music file
        mediaPlayer = MediaPlayer.create(context, R.raw.summer_song)

        // Get references to the ImageViews
        val segment1 = view.findViewById<ImageView>(R.id.segment1)
        val segment2 = view.findViewById<ImageView>(R.id.segment2)
        val segment3 = view.findViewById<ImageView>(R.id.segment3)

        // Set click listener for all segments
        segment1.setOnClickListener {
            // Play music when any segment is clicked
            mediaPlayer.start()
        }

        segment2.setOnClickListener {
            mediaPlayer.start()
        }

        segment3.setOnClickListener {
            mediaPlayer.start()
        }

        // Set the animation
        animation = AnimationUtils.loadAnimation(context, R.anim.move_n_repeat)

        // Get reference to the new ImageView
        val newImageView = view.findViewById<ImageView>(R.id.newImageView)

        // Set up a Handler to change the image periodically (slideshow effect)
        val imageResources = intArrayOf(
            R.drawable.autumn,
            R.drawable.summer,
            R.drawable.winter,
            R.drawable.spring
        )

        val handler = Handler()
        var imageIndex = 0
        val delayInMillis = 3000 // 3 seconds delay between image changes

        val runnable = object : Runnable {
            override fun run() {
                // Set the next image resource in the array
                newImageView.setImageResource(imageResources[imageIndex])

                // Increment the image index, and loop back to the first image if needed
                imageIndex = (imageIndex + 1) % imageResources.size

                // Schedule the next image change after the delay
                handler.postDelayed(this, delayInMillis.toLong())
            }
        }

        // Start the slideshow
        handler.postDelayed(runnable, delayInMillis.toLong())

        // Return the inflated view
        return view
    }

    // Function to apply animations to all ImageViews simultaneously
    private fun applyAnimations(vararg imageViews: ImageView) {
        val animators = mutableListOf<Animator>()

        for (imageView in imageViews) {
            imageView.startAnimation(animation)
            val scaleXAnimator = ObjectAnimator.ofFloat(imageView, View.SCALE_X, 1.0f, 1.2f, 1.0f)
            val scaleYAnimator = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, 1.0f, 1.2f, 1.0f)
            animators.add(scaleXAnimator)
            animators.add(scaleYAnimator)
        }

        // Add rotation animation for wheeloftime ImageView
        val wheelOfTime = view?.findViewById<ImageView>(R.id.wheeloftime)
        wheelOfTime?.let {
            val rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate)
            it.startAnimation(rotateAnimation)
        }

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animators)
        animatorSet.start()
    }

    // Function to start animations for all ImageViews simultaneously
    fun startAnimations() {
        val segment1 = view?.findViewById<ImageView>(R.id.segment1)
        val segment2 = view?.findViewById<ImageView>(R.id.segment2)
        val segment3 = view?.findViewById<ImageView>(R.id.segment3)

        applyAnimations(segment1!!, segment2!!, segment3!!)
    }

    // Function to stop animations for all ImageViews
    fun stopAnimations() {
        val segment1 = view?.findViewById<ImageView>(R.id.segment1)
        val segment2 = view?.findViewById<ImageView>(R.id.segment2)
        val segment3 = view?.findViewById<ImageView>(R.id.segment3)

        // Cancel the animation for each ImageView
        segment1?.animation?.cancel()
        segment2?.animation?.cancel()
        segment3?.animation?.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Release the MediaPlayer when the fragment's view is destroyed
        mediaPlayer.release()
    }
}
