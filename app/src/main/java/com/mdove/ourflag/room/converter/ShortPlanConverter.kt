package com.mdove.ourflag.room.converter

import androidx.room.TypeConverter
import com.mdove.android.base.gson.GsonProvider
import com.mdove.ourflag.plan.bean.ShortPlan


class ShortPlanConverter {
    @TypeConverter
    fun toShortPlan(value: String): ShortPlan {
        return GsonProvider.defaultGson.fromJson(value, ShortPlan::class.java)
    }

    @TypeConverter
    fun fromShortPlan(state: ShortPlan): String {
        return GsonProvider.defaultGson.toJson(state)
    }
}