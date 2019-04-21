package com.mdove.ourflag.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mdove.ourflag.plan.ShortPlan
import com.mdove.ourflag.room.ShortPlanConverter

/**
 * Created by MDove on 2019/4/21.
 */
@Entity(tableName = "short_plan_table")
@TypeConverters(value = [ShortPlanConverter::class])
class ShortPlanBean(@PrimaryKey(autoGenerate = true)
                    @SerializedName("id") var id: Long = 0,
                    @SerializedName("create_time") val createTime: Long,
                    @ColumnInfo(name = "plan") val plan: ShortPlan)