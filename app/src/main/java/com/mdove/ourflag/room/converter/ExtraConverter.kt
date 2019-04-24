package com.mdove.ourflag.room.converter

import androidx.room.TypeConverter
import com.mdove.android.base.gson.GsonProvider
import com.mdove.ourflag.plan.bean.ExtraDifficulty
import com.mdove.ourflag.plan.bean.ExtraReward

/**
 * Created by MDove on 2019/4/22.
 */
class ExtraConverter {
    @TypeConverter
    fun toExtraReward(value :String): ExtraReward {
        return GsonProvider.defaultGson.fromJson(value, ExtraReward::class.java)
    }

    @TypeConverter
    fun fromExtraReward(value: ExtraReward): String {
        return GsonProvider.defaultGson.toJson(value)
    }

    @TypeConverter
    fun toExtraDifficulty(value :String): ExtraDifficulty {
        return GsonProvider.defaultGson.fromJson(value, ExtraDifficulty::class.java)
    }

    @TypeConverter
    fun fromExtraDifficulty(value: ExtraDifficulty): String {
        return GsonProvider.defaultGson.toJson(value)
    }
}