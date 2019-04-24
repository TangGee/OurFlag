package com.mdove.android.uilib.dragview

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import androidx.recyclerview.widget.RecyclerView
import com.mdove.android.uilib.utils.UIUtils

/**
 * Created by MDove on 2019/4/2.
 */
class DragRootView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    private var innerRlvs: MutableList<RecyclerView> = mutableListOf()
    private var mDownTime: Long = 0
    private var mDownPoint: FloatArray? = null
    private var mCanceled: Boolean = false
    private var mHasComputer = false

    private var mTouchSlop: Int = 0
    private val mDragCallback = MyDragCallback()
    private val mViewDragHelper = ViewDragHelper.create(this, mDragCallback)

    var mInterceptClickListener: OnInterceptClickListener? = null
    var mOnInterceptTouchListener: View.OnTouchListener? = null

    var exitInvoke: (() -> Unit)? = null

    init {
        init(context)
    }

    private fun init(context: Context) {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (mOnInterceptTouchListener != null && mOnInterceptTouchListener!!.onTouch(this, event)) {
            return true
        }
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mDragCallback.determinedDrag = false
                mDownTime = System.currentTimeMillis()
                mDownPoint = floatArrayOf(event.x, event.y)
                mCanceled = false
            }
            MotionEvent.ACTION_UP -> if (!mCanceled
                && mTouchSlop >= Math.abs(event.x - mDownPoint!![0])
                && mTouchSlop >= Math.abs(event.y - mDownPoint!![1])) {
                if (mInterceptClickListener != null) {
                    return mInterceptClickListener!!.onClick(this)
                }
            }
            MotionEvent.ACTION_MOVE -> if (mTouchSlop < Math.abs(event.x - mDownPoint!![0]) || mTouchSlop < Math.abs(event.y - mDownPoint!![1])) {
                mCanceled = true
            }
            else -> mCanceled = true
        }

        return mViewDragHelper.shouldInterceptTouchEvent(event) || super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mViewDragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    interface OnInterceptClickListener {
        fun onClick(view: View): Boolean
    }

    private fun findAllRlv(view: ViewGroup) {
        if (mHasComputer) {
            return
        }
        val childCount = view.childCount
        for (index in 0..childCount) {
            val child = view.getChildAt(index)
            (child as? RecyclerView)?.let {
                innerRlvs.add(it)
            }
            if (child is ViewGroup) {
                findAllRlv(child)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        exitInvoke = null
    }

    private inner class MyDragCallback : ViewDragHelper.Callback() {

        internal var originTop: Int = 0
        internal var determinedDrag: Boolean = false

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            if (determinedDrag) {
                return false
            }
            var rlvIsTouch = false
            findAllRlv(this@DragRootView)
            mHasComputer = true
            if (!innerRlvs.isEmpty()) {
                val rect = Rect(0, 0, UIUtils.getScreenWidth(context), UIUtils.getScreenHeight(context))
                innerRlvs.forEach {
                    val isVisibleRect = it.getLocalVisibleRect(rect)
                    if (it.canScrollVertically(-1) && isVisibleRect) {
                        rlvIsTouch = true
                        return@forEach
                    }
                }
            }
            determinedDrag = true
            if (mViewDragHelper.viewDragState == ViewDragHelper.STATE_IDLE && !rlvIsTouch) {
                parent.requestDisallowInterceptTouchEvent(true)
                originTop = child.top
                return true
            }
            return false
        }

        override fun getViewVerticalDragRange(child: View): Int {
            return height - paddingTop
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            val innerView: Boolean
            innerView = when {
                yvel > 0 -> false
                yvel < 0 -> true
                else -> {
                    val topDelta = releasedChild.top - originTop
                    topDelta <= releasedChild.height / 2
                }
            }

            mViewDragHelper.smoothSlideViewTo(releasedChild, releasedChild.left, if (innerView) originTop else originTop + releasedChild.height)
            ViewCompat.postInvalidateOnAnimation(this@DragRootView)
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return if (top < originTop) {
                originTop
            } else top
        }

        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
//            val delta = changedView.top - originTop
//            val alpha = 1 - delta.toFloat() / changedView.height
//            ViewCompat.postInvalidateOnAnimation(this@DragRootView)

            if (mViewDragHelper.viewDragState == ViewDragHelper.STATE_SETTLING
                && top == originTop + changedView.height) {
                exitInvoke?.invoke()
            }
        }
    }
}
