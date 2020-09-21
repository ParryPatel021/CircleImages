package com.parrypatel.circle_images

import android.os.Bundle
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Sample image credit goes to https://developers.google.com/speed/webp/gallery2
 *
 */
class MainActivity : AppCompatActivity(), CircleClickListener {

    private val linearList = arrayListOf<LinearLayout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createButtons()
    }

    private fun createButtons() {
        val length = 8
        val angle = 360 / (length + 1)
        for (i in 0..length) {
            val linearLayout = LinearLayout(this)
            linearLayout.orientation = LinearLayout.VERTICAL
            val imageView = AppCompatImageView(this)
            imageView.id = View.generateViewId()
            imageView.background = ContextCompat.getDrawable(this, R.drawable.dr_round_bg)
            Glide.with(this)
                .load("https://www.gstatic.com/webp/gallery3/3_webp_ll.png")
                .into(imageView)

            val param = LinearLayout.LayoutParams(
                65.toPx(),
                0,
                1.0f
            )
            imageView.layoutParams = param

            val textView = AppCompatTextView(this)
            textView.text = String.format(Locale.ENGLISH, "My text $i")
            textView.gravity = Gravity.CENTER
            textView.ellipsize = TextUtils.TruncateAt.END
            textView.maxLines = 1

            linearLayout.addView(imageView)
            linearLayout.addView(textView)

            val layout = ConstraintLayout.LayoutParams(70.toPx(), 80.toPx())
            layout.circleRadius = 140.toPx()
            layout.circleConstraint = R.id.main_button
            layout.circleAngle = (i * angle).toFloat()
            layout.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            layout.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            layout.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            layout.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            linearLayout.layoutParams = layout
            linearList.add(linearLayout)
            main_layout.addView(linearLayout)
            linearLayout.setOnClickListener(View.OnClickListener {
                onCircleClick(i)
            })
        }
    }

    private fun Int.toPx(): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(), resources.displayMetrics
        ).toInt()

    override fun onCircleClick(position: Int) {
        Toast.makeText(
            this,
            "$position",
            Toast.LENGTH_SHORT
        ).show()
    }
}