package com.example.materialdesign

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.progress_indicators_determine.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.progress_indicators_determine)
        startAnim()
    }

    private fun startAnim() {
        val animator = ValueAnimator.ofInt(0, 100)
        animator.addUpdateListener {
            val progress = it.animatedValue as Int
            progress_horizontal.setProgressCompat(progress, true)
            if (progress == 100) {
                progress_horizontal.hide()
            }
        }
        animator.duration = 1000 * 10
        animator.interpolator = AccelerateInterpolator()
        animator.start()
    }
}

inline fun <reified T : Activity> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))
}