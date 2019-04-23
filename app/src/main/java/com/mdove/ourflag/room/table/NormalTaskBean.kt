package com.mdove.ourflag.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mdove.ourflag.plan.bean.NormalTask
import com.mdove.ourflag.room.converter.NormalBeanConverter

/**
 * Created by MDove on 2019/4/22.
 */
@Entity(tableName = "normal_task_table")
@TypeConverters(value = [NormalBeanConverter::class])
class NormalTaskBean(@PrimaryKey(autoGenerate = true)
                     @ColumnInfo(name = "id") var id: Long = 0,
                     @ColumnInfo(name = "create_time") val createTime: Long = System.currentTimeMillis(),
                     @ColumnInfo(name = "done") val done: Int = 1,//0表示完成
                     @ColumnInfo(name = "normal_task") val normalTask: NormalTask)