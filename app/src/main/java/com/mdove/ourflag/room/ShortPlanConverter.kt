package com.mdove.ourflag.room

import androidx.room.TypeConverter
import com.mdove.android.base.gson.GsonProvider
import com.mdove.ourflag.plan.ShortPlan


class ShortPlanConverter {
    @TypeConverter
    fun toState(value: String): ShortPlan {
        return GsonProvider.defaultGson.fromJson(value, ShortPlan::class.java)
    }

    @TypeConverter
    fun fromState(state: ShortPlan): String {
        return GsonProvider.defaultGson.toJson(state)
    }
}