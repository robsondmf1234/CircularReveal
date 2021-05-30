package com.example.circularreveal


import android.animation.ObjectAnimator
import android.animation.ObjectAnimator.ofFloat
import android.animation.ObjectAnimator.ofPropertyValuesHolder
import android.animation.PropertyValuesHolder
import android.animation.TimeInterpolator
import android.annotation.SuppressLint
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.example.circularreveal.anim.AnimationType
import com.example.circularreveal.anim.Easing

class ObjectAnimatorFactory {

    companion object {
        @SuppressLint("NewApi")
        fun createAnimation(animationVO: AnimationVO): ObjectAnimator {
            return when (animationVO.animationType) {
                AnimationType.MOVE_IN -> {
                    val fadeIn = PropertyValuesHolder.ofFloat("alpha",
                        0.0f,1.0f)
                    val transition =PropertyValuesHolder.ofFloat("TranslationY",
                        animationVO.initialPosition,
                        animationVO.finalPosition)
                    ofPropertyValuesHolder(animationVO.view,fadeIn,transition).apply {
                        duration = animationVO.duration.toLong()
                        interpolator = Easing.EaseOut
                    }
                }

                AnimationType.MOVE -> {
                    ofFloat(
                        animationVO.view,
                        "TranslationY",
                        animationVO.initialPosition,
                        animationVO.finalPosition,
                    ).apply {
                        duration = animationVO.duration.toLong()
                        interpolator =Easing.EaseinOut
                    }
                }

                AnimationType.MOVE_OUT -> {
                    val fadeOut = PropertyValuesHolder.ofFloat("alpha",
                        1.0f,0.0f)
                    val transition = PropertyValuesHolder.ofFloat("TranslationY",
                        animationVO.initialPosition,
                        animationVO.finalPosition)
                    ofPropertyValuesHolder(animationVO.view,fadeOut,transition).apply {
                        duration = animationVO.duration.toLong()
                        interpolator = Easing.EaseIn
                    }
                }

                AnimationType.MORPH -> {
                    TODO()
                    /*
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

                     */

                }


                AnimationType.POP -> {
                    if(animationVO.width < 1.0f){
                        animationVO.width + 1.0f
                    }else{
                        animationVO.width
                    }

                    if(animationVO.height < 1.0f){
                        animationVO.height + 1.0f
                    }else{
                        animationVO.height
                    }

                    val width =PropertyValuesHolder.ofFloat("scaleX",
                        animationVO.width)
                    val height =PropertyValuesHolder.ofFloat("scaleY",
                        animationVO.height)
                    ofPropertyValuesHolder(animationVO.view,width,height)
                        .apply {
                            duration = animationVO.duration.toLong()
                            interpolator = Easing.EaseinOut
                        }
                }

                AnimationType.SPIN -> {
                    ofFloat(
                        animationVO.view,"rotation",
                        animationVO.initialPosition,
                        animationVO.finalPosition,
                    ).apply {
                        duration = animationVO.duration.toLong()
                        interpolator = Easing.EaseinOut
                    }
                }

                AnimationType.SHRINK -> {
                    var widthTemp = 0f
                    var heightTemp = 0f

                    if (animationVO.width>1.0f || animationVO.width < 0.0f){
                        widthTemp = 1.0f
                    }else{
                        widthTemp = animationVO.width
                    }
                    if (animationVO.height>1.0f || animationVO.height < 0.0f){
                        heightTemp = 1.0f
                    }else{
                        heightTemp = animationVO.height
                    }

                    val width =PropertyValuesHolder.ofFloat("scaleX",
                        widthTemp)
                    val height =PropertyValuesHolder.ofFloat("scaleY",
                        heightTemp)
                    ofPropertyValuesHolder(animationVO.view,width,height)
                        .apply {
                            duration = animationVO.duration.toLong()
                            interpolator = Easing.EaseinOut
                        }
                }
            }
        }
    }
}

data class AnimationVO(
    val view: View,
    val duration: Int,
    val animationType: AnimationType,
    val initialPosition: Float,
    val finalPosition:Float,
    val width:Float=0.0f,
    val height:Float=0.0f
)
