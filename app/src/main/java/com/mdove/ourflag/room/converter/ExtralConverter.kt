package com.mdove.ourflag.room.converter

import androidx.room.TypeConverter
import com.mdove.android.base.gson.GsonProvider
import com.mdove.ourflag.plan.bean.ExtraReward

/**
 * Created by MDove on 2019/4/22.
 */
class ExtralConverter {
    @TypeConverter
    fun toExtralReward(value :String): ExtraReward {
        return GsonProvider.defaultGson.fromJson(value, ExtraReward::class.java)
    }

    @TypeConverter
    fun fromExtralReward(value: ExtraReward): String {
        return GsonProvider.defaultGson.toJson(value)
    }
}