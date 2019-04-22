package com.mdove.android.uilib.tablayout.utils

import android.annotation.SuppressLint
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import androidx.viewpager.widget.ViewPager
import com.mdove.android.uilib.tablayout.SlidingTabLayout

/**
 * Created by MDove on 2019/4/22.
 */
@SuppressLint("RestrictedApi")
fun ArgbEvaluator.calculateGradualColor(positionOffset: Float, fromColor: Int, toColor: Int): Int {
    return evaluate(positionOffset, fromColor, toColor) as Int
}

fun ViewPager.scrollNextPosition(position: Int): Int {
    var currentPosition = currentItem
    return if (currentPosition == position) {//按下向左拖动
        currentPosition + 1
    } else {//按下向右拖动
        position
    }
}


fun SlidingTabLayout.gradualColor(currentPosition: Int, nextPosition: Int, currentPositionColor: Int, nextPositionColor: Int) {
    var current = currentPositionColor
    var next = nextPositionColor
    if (currentPosition < nextPosition) {////按下向左拖动
    } else {//按下向右拖动，根据onPageScrolled回调适配
        current = nextPositionColor
        next = currentPositionColor
    }
    getTitleView(currentPosition).setTextColor(current)
    getTitleView(nextPosition).setTextColor(next)
}