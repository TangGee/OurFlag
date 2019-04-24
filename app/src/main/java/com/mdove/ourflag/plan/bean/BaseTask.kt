package com.mdove.ourflag.plan.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by MDove on 2019/4/22.
 */
@Parcelize
data class BaseTask(@SerializedName("tag") val tag: String,
                    @SerializedName("title") val title: String?,
                    @SerializedName("content") val content: String,
                    @SerializedName("complete_time") val completeTime: Long) : Parcelable