package com.mdove.ourflag.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mdove.ourflag.plan.bean.ShortPlan
import com.mdove.ourflag.room.converter.ShortPlanConverter

/**
 * Created by MDove on 2019/4/21.
 */
@Entity(tableName = "short_plan_table")
@TypeConverters(value = [ShortPlanConverter::class])
class ShortPlanBean(@PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name="id") var id: Long = 0,
                    @ColumnInfo(name="create_time") val createTime: Long,
                    @ColumnInfo(name = "plan") val plan: ShortPlan)