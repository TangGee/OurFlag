package com.mdove.ourflag.room

import androidx.room.TypeConverter
import com.mdove.ourflag.plan.ShortPlan
import com.mdove.ourflag.utils.GsonProvider


class ShortPlanConverter {
    @TypeConverter
    fun toState(value: String): ShortPlan {
        return GsonProvider.getDefaultGson().fromJson(value, ShortPlan::class.java)
    }

    @TypeConverter
    fun fromState(state: ShortPlan): String {
        return GsonProvider.getDefaultGson().toJson(state)
    }
}