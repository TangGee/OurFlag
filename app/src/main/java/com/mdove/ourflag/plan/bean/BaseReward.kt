package com.mdove.ourflag.plan.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by MDove on 2019/4/22.
 */
@Parcelize
data class BaseReward(
        @SerializedName("level") val level: Int = 1,
        @SerializedName("exp") val exp: Int = levelToExp(level)) : Parcelable

fun levelToExp(level: Int): Int {
    return when (level) {
        1 -> 10
        2 -> 20
        3 -> 30
        4 -> 40
        else -> 10
    }
}

