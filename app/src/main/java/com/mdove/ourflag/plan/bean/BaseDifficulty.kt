package com.mdove.ourflag.plan.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by MDove on 2019/4/22.
 */
@Parcelize
data class BaseDifficulty(@SerializedName("level_tag") val levelTag: String,
                          @SerializedName("level") val level: Int) : Parcelable

fun initLevel(level: Int):BaseDifficulty{
    return when(level){
        1 -> BaseDifficulty("简单",1)
        2 -> BaseDifficulty("困难",2)
        3 -> BaseDifficulty("地狱",3)
        4 -> BaseDifficulty("深渊",4)
        else -> BaseDifficulty("深渊",4)
    }
}