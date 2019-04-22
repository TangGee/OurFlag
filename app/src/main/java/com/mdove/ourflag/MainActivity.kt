package com.mdove.ourflag

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import androidx.viewpager.widget.ViewPager
import com.mdove.android.uilib.tablayout.utils.calculateGradualColor
import com.mdove.android.uilib.tablayout.utils.gradualColor
import com.mdove.android.uilib.tablayout.utils.scrollNextPosition
import com.mdove.ourflag.base.AbsActivity
import com.mdove.ourflag.plan.NoDoneTaskFragment
import com.mdove.ourflag.plan.viewmodel.ShortPlanViewModel
import kotlinx.android.synthetic.main.activity_main.*
import com.mdove.ourflag.R

class MainActivity : AbsActivity() {
    private lateinit var viewModel: ShortPlanViewModel
    private lateinit var DEFAULT_TITILES: ArrayList<String>
    @SuppressLint("RestrictedApi")
    private val evaluator = ArgbEvaluator()
    private lateinit var DEFAULT_INDICATOR_COLORS: ArrayList<Int>
    private var selectedColor: Int = 0
    private var unSelectedColor: Int = 0

    companion object {
        const val CHANNEL_INDEX_NO_TASK = 0
        const val CHANNEL_INDEX_TODAY_TASK = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(ShortPlanViewModel::class.java)
        initData()

        if (savedInstanceState == null) {
            initViewPager()
            initTablayout()
        }
    }

    private fun initData() {
        DEFAULT_TITILES = arrayListOf(getString(R.string.string_title_no_done_task_fragment),
                getString(R.string.string_title_today_task_fragment))
        DEFAULT_INDICATOR_COLORS = arrayListOf(ContextCompat.getColor(this, R.color.indicator_start_color), ContextCompat.getColor(this, R.color.indicator_end_color))

        selectedColor = ContextCompat.getColor(this, R.color.black)
        unSelectedColor = ContextCompat.getColor(this, R.color.black_50)
    }

    private fun initViewPager() {
        view_pager?.adapter = ViewPagerAdapter(DEFAULT_TITILES, supportFragmentManager)
        val pageChangeListener = object : ViewPager.OnPageChangeListener {
            var pageSelected = false

            override fun onPageScrollStateChanged(state: Int) {
                if (0 == state) pageSelected = false
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (positionOffset > 0.0f && !pageSelected) {
                    tabs.gradualColor(view_pager.currentItem, view_pager.scrollNextPosition(position),
                            evaluator.calculateGradualColor(positionOffset, selectedColor, unSelectedColor),
                            evaluator.calculateGradualColor(positionOffset, unSelectedColor, selectedColor))
                }
            }

            override fun onPageSelected(position: Int) {
            }
        }
        view_pager?.addOnPageChangeListener(pageChangeListener)
    }

    private fun initTablayout() {
        val colors = mutableListOf<Int>()
        view_pager?.adapter?.let { it_ ->
            (0 until it_.count).forEach { index ->
                colors.add(DEFAULT_INDICATOR_COLORS[index % DEFAULT_INDICATOR_COLORS.size])
            }
        }
        tabs.setViewPager(view_pager, DEFAULT_TITILES, DEFAULT_INDICATOR_COLORS)
    }

    class ViewPagerAdapter(val categoryList: List<String>, private val fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                CHANNEL_INDEX_NO_TASK -> {
                    NoDoneTaskFragment.instance(null)
                }
                CHANNEL_INDEX_TODAY_TASK -> {
                    NoDoneTaskFragment.instance(null)
                }
                else -> throw Exception("Index out of bound")
            }
        }

        override fun getCount(): Int {
            return categoryList.size
        }
    }
}
