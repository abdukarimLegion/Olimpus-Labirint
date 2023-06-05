package com.test11.rocketgame.widgets

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar
import android.widget.TextView


class ProgressBarAnimation(
    var context: Context,
    var progress : ProgressBar,
    var textView: TextView,
    var from : Float,
    var to : Float,

    var activity: Activity
): Animation() {
    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val value = from + (to - from) * interpolatedTime
        progress.progress = value.toInt()
        textView.text = "${value.toInt() } sec"

        if (value == to){
//            context.startActivity(Intent(context, HomeActivity::class.java))
//            activity.finish()
        }
    }
}