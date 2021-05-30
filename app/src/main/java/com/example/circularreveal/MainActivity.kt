package com.example.circularreveal

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.button1
import kotlinx.android.synthetic.main.activity_main.view1
import kotlinx.android.synthetic.main.activity_main.view2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Teste", Toast.LENGTH_SHORT).show()
            animacao()
        })
    }

    private fun animacao() {
        // previously invisible view
        val myView: View = view1
        val myView2: View = view2

        // Check if the runtime version is at least Lollipop
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // get the center for the clipping circle
            val cx = myView.width / 2
            val cy = myView.height / 2

            // get the final radius for the clipping circle
            val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

            val anim1 = ViewAnimationUtils.createCircularReveal(myView, cx, cy, finalRadius,
                myView.width.toFloat() / 2
            )
            val fadeIn = ObjectAnimator.ofFloat(myView,"alpha",
                    1.0f,0.0f).apply {
                duration = 1000
            }
            val fade = ObjectAnimator.ofFloat(myView,"alpha",
                0.0f,0.0f).apply {
                duration = 2000
            }

            // make the view visible and start the animation
            anim1.duration = 1000


            val animator = AnimatorSet()
            animator.play(anim1)
            animator.play(fade).after(anim1)
            animator.start()
            myView2.visibility = View.VISIBLE

        } else {
            // set the view to invisible without a circular reveal animation below Lollipop
           // myView.visibility = View.INVISIBLE
        }
    }
}