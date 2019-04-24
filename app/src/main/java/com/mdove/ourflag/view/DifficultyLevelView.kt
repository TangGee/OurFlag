package com.mdove.ourflag.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.mdove.ourflag.R
import com.mdove.ourflag.plan.bean.levelToTag
import kotlinx.android.synthetic.main.view_difficulty_level.view.*

/**
 * Created by MDove on 2019/4/24.
 */
class DifficultyLevelView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var level1IsSelect = false
    private var level2IsSelect = false
    private var level3IsSelect = false
    private var level4IsSelect = false

    init {
        LayoutInflater.from(context).inflate(R.layout.view_difficulty_level, this)
        tv_level_1.text = levelToTag(1)
        tv_level_2.text = levelToTag(2)
        tv_level_3.text = levelToTag(3)
        tv_level_4.text = levelToTag(4)

        tv_level_1.setOnClickListener {
            level1IsSelect = !level1IsSelect
            switchBg(level1IsSelect, 1)
        }
        tv_level_2.setOnClickListener {
            level2IsSelect = !level2IsSelect
            switchBg(level2IsSelect, 2)
        }
        tv_level_3.setOnClickListener {
            level3IsSelect = !level3IsSelect
            switchBg(level3IsSelect, 3)
        }
        tv_level_4.setOnClickListener {
            level4IsSelect = !level4IsSelect
            switchBg(level4IsSelect, 4)
        }
    }

    fun initBg(selectLevel: Int) {
        for (it in 1..4) {
            switchBg(selectLevel == it, it)
        }
    }

    private fun switchBg(select: Boolean, level: Int) {
        resetSelect(select, level)
        when (level) {
            1 -> {
                tv_level_1.setBackgroundResource(if (select) R.drawable.bg_select_difficulty_level_1 else R.drawable.bg_no_select_difficulty_level_1)
                tv_level_1.setTextColor(if (select) ContextCompat.getColor(context, R.color.white) else ContextCompat.getColor(context, R.color.difficulty_color_1))
                unSelectBg(2)
                unSelectBg(3)
                unSelectBg(4)
            }
            2 -> {
                tv_level_2.setBackgroundResource(if (select) R.drawable.bg_select_difficulty_level_2 else R.drawable.bg_no_select_difficulty_level_2)
                tv_level_2.setTextColor(if (select) ContextCompat.getColor(context, R.color.white) else ContextCompat.getColor(context, R.color.difficulty_color_2))
                unSelectBg(1)
                unSelectBg(3)
                unSelectBg(4)
            }
            3 -> {
                tv_level_3.setBackgroundResource(if (select) R.drawable.bg_select_difficulty_level_3 else R.drawable.bg_no_select_difficulty_level_3)
                tv_level_3.setTextColor(if (select) ContextCompat.getColor(context, R.color.white) else ContextCompat.getColor(context, R.color.difficulty_color_3))
                unSelectBg(1)
                unSelectBg(2)
                unSelectBg(4)
            }
            4 -> {
                tv_level_4.setBackgroundResource(if (select) R.drawable.bg_select_difficulty_level_4 else R.drawable.bg_no_select_difficulty_level_4)
                tv_level_4.setTextColor(if (select) ContextCompat.getColor(context, R.color.white) else ContextCompat.getColor(context, R.color.difficulty_color_4))
                unSelectBg(1)
                unSelectBg(2)
                unSelectBg(3)
            }
            else -> {
                tv_level_1.setBackgroundResource(if (select) R.drawable.bg_select_difficulty_level_1 else R.drawable.bg_no_select_difficulty_level_1)
                tv_level_1.setTextColor(if (select) ContextCompat.getColor(context, R.color.white) else ContextCompat.getColor(context, R.color.difficulty_color_1))
                unSelectBg(2)
                unSelectBg(3)
                unSelectBg(4)
            }
        }
    }

    private fun resetSelect(select: Boolean, level: Int) {
        when (level) {
            1 -> {
                level1IsSelect = select
                level2IsSelect = false
                level3IsSelect = false
                level4IsSelect = false
            }
            2 -> {
                level1IsSelect = false
                level2IsSelect = select
                level3IsSelect = false
                level4IsSelect = false
            }
            3 -> {
                level1IsSelect = false
                level2IsSelect = false
                level3IsSelect = select
                level4IsSelect = false
            }
            4 -> {
                level1IsSelect = false
                level2IsSelect = false
                level3IsSelect = false
                level4IsSelect = select
            }
            else -> {
                level1IsSelect = select
                level2IsSelect = false
                level3IsSelect = false
                level4IsSelect = false
            }
        }
    }

    private fun unSelectBg(level: Int) {
        when (level) {
            1 -> {
                tv_level_1.setBackgroundResource(R.drawable.bg_no_select_difficulty_level_1)
                tv_level_1.setTextColor(ContextCompat.getColor(context, R.color.difficulty_color_1))
            }
            2 -> {
                tv_level_2.setBackgroundResource(R.drawable.bg_no_select_difficulty_level_2)
                tv_level_2.setTextColor(ContextCompat.getColor(context, R.color.difficulty_color_2))
            }
            3 -> {
                tv_level_3.setBackgroundResource(R.drawable.bg_no_select_difficulty_level_3)
                tv_level_3.setTextColor(ContextCompat.getColor(context, R.color.difficulty_color_3))
            }
            4 -> {
                tv_level_4.setBackgroundResource(R.drawable.bg_no_select_difficulty_level_4)
                tv_level_4.setTextColor(ContextCompat.getColor(context, R.color.difficulty_color_4))
            }
            else -> {
                tv_level_1.setBackgroundResource(R.drawable.bg_no_select_difficulty_level_1)
                tv_level_1.setTextColor(ContextCompat.getColor(context, R.color.difficulty_color_1))
            }
        }
    }
}