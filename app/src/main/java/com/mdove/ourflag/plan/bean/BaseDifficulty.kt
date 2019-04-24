package com.mdove.ourflag.plan.bean

import android.graphics.Color
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by MDove on 2019/4/22.
 */
@Parcelize
data class BaseDifficulty(@SerializedName("level_tag") val levelTag: String,
                          @SerializedName("level") val level: Int,
                          @SerializedName("level_color") val tagColor: Int) : Parcelable

fun initLevel(level: Int): BaseDifficulty {
    return when (level) {
        1 -> BaseDifficulty("简单", 1, Color.parseColor("#808080"))
        2 -> BaseDifficulty("困难", 2, Color.parseColor("#039BE5"))
        3 -> BaseDifficulty("地狱", 3, Color.parseColor("#512DA8"))
        4 -> BaseDifficulty("深渊", 4, Color.parseColor("#D32F2F"))
        else -> BaseDifficulty("深渊", 4, Color.parseColor("#D32F2F"))
    }
}

fun levelToColor(level: Int): Int {
    return when (level) {
        1 -> Color.parseColor("#808080")
        2 -> Color.parseColor("#039BE5")
        3 -> Color.parseColor("#512DA8")
        4 -> Color.parseColor("#D32F2F")
        else -> Color.parseColor("#D32F2F")
    }
}

fun levelToTag(level: Int): String {
    return when (level) {
        1 -> "简单"
        2 -> "困难"
        3 -> "地狱"
        4 -> "深渊"
        else -> "深渊"
    }
}