package com.mdove.ourflag.base.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by MDove on 2019/4/24.
 */
@Parcelize
data class UserInfo(@SerializedName("create_time") val createTime: Long = System.currentTimeMillis(),
                    @SerializedName("autonomy") val autonomy: Long,
                    @SerializedName("exp") val exp: Long) : Parcelable