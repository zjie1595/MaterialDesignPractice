package com.example.materialdesign

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.TransitionManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.slider.RangeSlider
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.progress_indicators_determine.*
import kotlinx.android.synthetic.main.sheets.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sheets)

        show.setOnClickListener {
            val materialFade = MaterialFade().apply {
                duration = 150L
            }
            val sharedAxis = MaterialSharedAxis(MaterialSharedAxis.X, true)
            TransitionManager.beginDelayedTransition(root, sharedAxis)
            support_card.visibility = View.VISIBLE
        }
        hide.setOnClickListener {
            val materialFade = MaterialFade().apply {
                duration = 84L
            }
            val sharedAxis = MaterialSharedAxis(MaterialSharedAxis.X, false)
            TransitionManager.beginDelayedTransition(root, sharedAxis)
            support_card.visibility = View.GONE
        }
    }

    private fun setSheet(button: Button, view: LinearLayout) {
        button.setOnClickListener {
            val behavior = BottomSheetBehavior.from(view)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
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
