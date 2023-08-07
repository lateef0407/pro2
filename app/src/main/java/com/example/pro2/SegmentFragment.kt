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
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pro2.R
import java.text.SimpleDateFormat
import java.util.*

class SegmentFragment : Fragment() {
    // MediaPlayer instance
    private lateinit var mediaPlayer: MediaPlayer

    // Animation used for scaling
    private lateinit var animation: Animation

    private var currentStep = 0
    private val steps = listOf(
        Step(R.color.yellow, R.raw.summer_song, 0),
        Step(R.color.white, R.raw.spring_song, 15000),
        Step(R.color.OrangeRed, R.raw.autumn_song, 30000),
        Step(R.color.darkseagreen, R.raw.winter_song, 45000)
    )

    val backgroundColors = intArrayOf(
        R.color.yellow,
        R.color.white,
        R.color.OrangeRed,
        R.color.darkseagreen
    )

    private val backgroundColorChangeDelay = 5000L // 5 seconds
    private val backgroundColorHandler = Handler()

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

        // Get reference to the TextView to display current date and time
        val currentTimeTextView = view.findViewById<TextView>(R.id.currentTimeTextView)

        // Start a Handler to update the current date and time every second
        val updateIntervalInMillis = 1000 // 1 second interval

        val timeRunnable = object : Runnable {
            override fun run() {
                // Get the current date and time
                val currentDateAndTime = getCurrentDateAndTime()

                // Update the TextView with the current date and time
                currentTimeTextView.text = currentDateAndTime

                // Schedule the next update after the delay
                handler.postDelayed(this, updateIntervalInMillis.toLong())
            }
        }

        // Start updating the current date and time
        handler.postDelayed(timeRunnable, updateIntervalInMillis.toLong())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the animations and color changes, but don't start them immediately
        // Instead, start them when the "Start" button is clicked
        // startAnimations()
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

        // Apply background color change and play music based on currentStep
        val step = steps[currentStep]
        updateBackgroundColor(step.backgroundColor)
        playMusic(step.musicResId)

        // Increment currentStep and reset to 0 if it reaches the end
        currentStep = (currentStep + 1) % steps.size
    }

    // Function to start animations for all ImageViews simultaneously
    fun startAnimations() {
        val segment1 = view?.findViewById<ImageView>(R.id.segment1)
        val segment2 = view?.findViewById<ImageView>(R.id.segment2)
        val segment3 = view?.findViewById<ImageView>(R.id.segment3)

        applyAnimations(segment1!!, segment2!!, segment3!!)

        // Start the background color change task
        startBackgroundColorChangeTask()
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

        // Reset background color and stop the music
        updateBackgroundColor(steps[currentStep].backgroundColor)
        stopMusic()
    }

    fun updateBackgroundColor(colorResId: Int) {
        val color = context?.let { context -> resources.getColor(colorResId, context.theme) }
        view?.setBackgroundColor(color ?: 0)
    }

    private fun playMusic(musicResId: Int) {
        mediaPlayer.stop()
        mediaPlayer.reset()
        mediaPlayer = MediaPlayer.create(requireContext(), musicResId)
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
        }
    }

    private fun stopMusic() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    private fun getCurrentDateAndTime(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val currentDateAndTime = Date()
        return dateFormat.format(currentDateAndTime)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release()
        backgroundColorHandler.removeCallbacksAndMessages(null)
    }

    private fun startBackgroundColorChangeTask() {
        backgroundColorHandler.removeCallbacksAndMessages(null)
        backgroundColorHandler.postDelayed(backgroundTask, backgroundColorChangeDelay)
    }

    private val backgroundTask = object : Runnable {
        override fun run() {
            currentStep = (currentStep + 1) % steps.size

            // Apply background color change and play music based on currentStep
            val step = steps[currentStep]
            updateBackgroundColor(step.backgroundColor)

            // Schedule the next background color change after the delay
            backgroundColorHandler.postDelayed(this, backgroundColorChangeDelay)
        }
    }

    data class Step(val backgroundColor: Int, val musicResId: Int, val delayInMillis: Int)
}
