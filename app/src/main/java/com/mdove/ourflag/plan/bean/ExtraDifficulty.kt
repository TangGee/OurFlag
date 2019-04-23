package com.mdove.ourflag.plan.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by MDove on 2019/4/23.
 */
@Parcelize
data class ExtraDifficulty(
        @SerializedName("autonomy") val autonomy: Int = 0) : Parcelable