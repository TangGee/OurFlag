package com.mdove.ourflag.plan.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mdove.ourflag.plan.adapter.diff.NoDoneTaskDiffUtils
import com.mdove.ourflag.plan.adapter.viewholder.AddTaskItemBinder
import com.mdove.ourflag.plan.adapter.viewholder.NormalTaskItemBinder
import com.mdove.ourflag.plan.adapter.viewholder.ReceiveTodayTaskItemBinder
import com.mdove.ourflag.plan.bean.AddTaskItemBean
import com.mdove.ourflag.plan.bean.BaseTaskListItem
import com.mdove.ourflag.plan.bean.NormalTaskItemBean
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Created by MDove on 2019/4/24.
 */
class ReceiveTodayTaskAdapter(clickTopOption: () -> Unit, itemClick: (taskBean:NormalTaskItemBean) -> Unit) : MultiTypeAdapter() {
    private val addTaskItemBinder = AddTaskItemBinder(clickTopOption)
    private val normalTaskItemBinder = ReceiveTodayTaskItemBinder(itemClick)
    private val diffCallback = NoDoneTaskDiffUtils()

    init {
        register(AddTaskItemBean::class.java, addTaskItemBinder)
        register(NormalTaskItemBean::class.java, normalTaskItemBinder)
    }

    fun updateData(items: List<BaseTaskListItem>) {
        this.items = items
        diffCallback.update(items)
        DiffUtil.calculateDiff(diffCallback, false).dispatchUpdatesTo(this)
    }
}