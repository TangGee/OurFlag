package com.mdove.ourflag.plan.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by MDove on 2019/4/22.
 */
@Parcelize
data class NormalTask(@SerializedName("base_task") val baseTask: BaseTask,
                      @SerializedName("base_reward") val baseReward: BaseReward,
                      @SerializedName("base_difficulty") val baseDifficulty: BaseDifficulty) : Parcelable