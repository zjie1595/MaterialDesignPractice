package com.example.materialdesign

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import kotlinx.android.synthetic.main.progress_indicators_determine.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_field_theme)

    }

    private fun setInputDropDownMenu(editText: AutoCompleteTextView) {
        val items = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        (editText as? AutoCompleteTextView)?.setAdapter(adapter)
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

    private fun setLabelFormatter(rangeSlider: RangeSlider) {
        rangeSlider.setLabelFormatter { value ->
            val format = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance("USD")
            format.format(value.toDouble())
        }
    }
}

inline fun <reified T : Activity> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))
}
