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