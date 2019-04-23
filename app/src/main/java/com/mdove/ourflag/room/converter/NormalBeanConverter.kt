package com.mdove.ourflag.room.converter

import androidx.room.TypeConverter
import com.mdove.android.base.gson.GsonProvider
import com.mdove.ourflag.plan.bean.BaseDifficulty
import com.mdove.ourflag.plan.bean.BaseReward
import com.mdove.ourflag.plan.bean.NormalTask

/**
 * Created by MDove on 2019/4/22.
 */
class NormalBeanConverter {
    @TypeConverter
    fun toNormalTask(value: String): NormalTask {
        return GsonProvider.defaultGson.fromJson(value, NormalTask::class.java)
    }

    @TypeConverter
    fun fromNormalTask(value: NormalTask): String {
        return GsonProvider.defaultGson.toJson(value)
    }

    // Reward
    @TypeConverter
    fun toBaseReward(value: String): BaseReward {
        return GsonProvider.defaultGson.fromJson(value, BaseReward::class.java)
    }

    @TypeConverter
    fun fromBaseReward(value: BaseReward): String {
        return GsonProvider.defaultGson.toJson(value)
    }

    // Difficulty
    @TypeConverter
    fun toBaseDifficulty(value: String): BaseDifficulty {
        return GsonProvider.defaultGson.fromJson(value, BaseDifficulty::class.java)
    }

    @TypeConverter
    fun fromBaseDifficulty(value: BaseDifficulty): String {
        return GsonProvider.defaultGson.toJson(value)
    }
}