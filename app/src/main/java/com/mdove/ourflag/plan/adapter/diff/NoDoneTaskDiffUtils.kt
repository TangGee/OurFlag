package com.mdove.ourflag.plan.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.mdove.ourflag.plan.bean.AddTaskItemBean
import com.mdove.ourflag.plan.bean.NormalTaskItemBean

/**
 * Created by MDove on 2019/4/22.
 */
class NoDoneTaskDiffUtils : DiffUtil.Callback() {
    private var oldData = emptyList<Any>()
    private var data = oldData

    fun update(data: List<Any>) {
        oldData = this.data
        this.data = data
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldData[oldItemPosition]
        val newItem = data[newItemPosition]

        if (oldItem is NormalTaskItemBean && newItem is NormalTaskItemBean) {
            return oldItem.createTime == newItem.createTime
        }

        if (oldItem is AddTaskItemBean && newItem is AddTaskItemBean) {
            return true
        }

        return oldItem === newItem
    }

    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return data.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldData[oldItemPosition]
        val newItem = data[newItemPosition]
        return if (oldItem is NormalTaskItemBean && newItem is NormalTaskItemBean) {
            oldItem.done == newItem.done
        } else if (oldItem is NormalTaskItemBean && newItem is NormalTaskItemBean) {
            oldItem.normalTask.baseTask.content == newItem.normalTask.baseTask.content
        } else {
            true
        }
    }
}