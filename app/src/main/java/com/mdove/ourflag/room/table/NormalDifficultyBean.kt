package com.mdove.ourflag.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mdove.ourflag.plan.bean.BaseDifficulty
import com.mdove.ourflag.plan.bean.ExtraReward
import com.mdove.ourflag.room.converter.ExtraConverter
import com.mdove.ourflag.room.converter.NormalBeanConverter

/**
 * Created by MDove on 2019/4/23.
 */
@Entity(tableName = "normal_difficulty_table")
@TypeConverters(value = [NormalBeanConverter::class, ExtraConverter::class])
class NormalDifficultyBean(@PrimaryKey(autoGenerate = true)
                           @ColumnInfo(name = "id") var id: Long = 0,
                           @ColumnInfo(name = "normal_reward") val normalReward: BaseDifficulty,
                           @ColumnInfo(name = "extra_reward") val extraReward: ExtraReward)