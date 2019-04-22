package com.mdove.ourflag.plan.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

/**
 * Created by MDove on 2019/4/22.
 */
@Parcelize
data class NormalTask(@SerializedName("base_task") val baseTask: BaseTask,
                      @SerializedName("base_reward") val baseReward: BaseReward = BaseReward(),
                      @SerializedName("base_difficulty") val baseDifficulty: BaseDifficulty = initLevel(Random.nextInt(3) + 1)) : Parcelable