package com.mdove.ourflag.plan.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by MDove on 2019/4/21.
 */
@Parcelize
data class ShortPlan(@SerializedName("tag") val tag: String,
                     @SerializedName("content") val content: String) : Parcelable