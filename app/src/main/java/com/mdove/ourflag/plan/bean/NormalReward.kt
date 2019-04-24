package com.mdove.ourflag.plan.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by MDove on 2019/4/24.
 */
@Parcelize
data class NormalReward(@SerializedName("level") val level: Int = 1,
                        @SerializedName("level_exp") val exp: Int = levelToExp(level),
                        @SerializedName("level_tag") val levelTag: String = levelToTag(level),
                        @SerializedName("level_color") val tagColor: Int = levelToColor(level)) : Parcelable