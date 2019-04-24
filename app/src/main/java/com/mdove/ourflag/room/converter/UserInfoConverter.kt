package com.mdove.ourflag.room.converter

import androidx.room.TypeConverter
import com.mdove.android.base.gson.GsonProvider
import com.mdove.ourflag.base.bean.UserInfo

/**
 * Created by MDove on 2019/4/24.
 */
class UserInfoConverter {
    @TypeConverter
    fun toUserInfo(value :String): UserInfo {
        return GsonProvider.defaultGson.fromJson(value, UserInfo::class.java)
    }

    @TypeConverter
    fun fromUserInfo(value: UserInfo): String {
        return GsonProvider.defaultGson.toJson(value)
    }
}