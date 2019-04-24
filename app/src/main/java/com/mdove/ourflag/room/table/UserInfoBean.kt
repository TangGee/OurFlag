package com.mdove.ourflag.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mdove.ourflag.base.bean.UserInfo
import com.mdove.ourflag.room.converter.UserInfoConverter

/**
 * Created by MDove on 2019/4/24.
 */
@Entity(tableName = "user_info_table")
@TypeConverters(value = [UserInfoConverter::class])
class UserInfoBean(@PrimaryKey(autoGenerate = true)
                   @ColumnInfo(name = "id") var id: Long = 0,
                   @ColumnInfo(name = "user_info") val userInfo: UserInfo)