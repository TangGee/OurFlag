package com.mdove.ourflag.plan.bean

import com.google.gson.annotations.SerializedName
import com.mdove.ourflag.room.table.NormalTaskBean

/**
 * Created by MDove on 2019/4/22.
 */
sealed class BaseTaskListItem(val isRealBean: Boolean = true)

object AddTaskItemBean : BaseTaskListItem(false)

data class NormalTaskItemBean(@SerializedName("id") val id: Long,
                              @SerializedName("create_time") val createTime: Long,
                              @SerializedName("done") val done: Int,//0表示完成
                              @SerializedName("normal_task") val normalTask: NormalTask) : BaseTaskListItem()

fun NormalTaskBean.toNormalTaskItemBean(): NormalTaskItemBean {
    return NormalTaskItemBean(id = id, createTime = this.createTime, done = this.done, normalTask = this.normalTask)
}

fun NormalTaskItemBean.toNormalTaskBean(): NormalTaskBean {
    return NormalTaskBean(id = id, createTime = this.createTime, done = this.done, normalTask = this.normalTask)
}