package com.mdove.ourflag.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mdove.ourflag.plan.bean.BaseReward
import com.mdove.ourflag.plan.bean.ExtraReward
import com.mdove.ourflag.room.converter.ExtraConverter
import com.mdove.ourflag.room.converter.NormalBeanConverter

/**
 * Created by MDove on 2019/4/22.
 */
@Entity(tableName = "normal_reward_table")
@TypeConverters(value = [NormalBeanConverter::class, ExtraConverter::class])
class NormalRewardBean(@PrimaryKey(autoGenerate = true)
                       @ColumnInfo(name = "id") var id: Long = 0,
                       @ColumnInfo(name = "normal_reward") val normalReward: BaseReward,
                       @ColumnInfo(name = "extra_reward") val extraReward: ExtraReward)