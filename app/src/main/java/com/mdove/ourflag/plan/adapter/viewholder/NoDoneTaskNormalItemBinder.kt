package com.mdove.ourflag.plan.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mdove.android.base.utils.DateUtils
import com.mdove.ourflag.R
import com.mdove.ourflag.plan.bean.NormalTaskItemBean
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by MDove on 2019/4/22.
 */
class NoDoneTaskNormalItemBinder(val click: (taskBean: NormalTaskItemBean) -> Unit) : ItemViewBinder<NormalTaskItemBean, NoDoneTaskNormalItemViewHolder>() {
    override fun onBindViewHolder(viewHolder: NoDoneTaskNormalItemViewHolder, data: NormalTaskItemBean) {
        viewHolder.bindData(data)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): NoDoneTaskNormalItemViewHolder {
        return NoDoneTaskNormalItemViewHolder(inflater, parent, click)
    }
}

class NoDoneTaskNormalItemViewHolder(inflater: LayoutInflater, parent: ViewGroup, val click: (taskBean: NormalTaskItemBean) -> Unit) : RecyclerView.ViewHolder(
        inflater.inflate(R.layout.item_no_done_task_normal, parent, false)) {
    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val tvContent: TextView = itemView.findViewById(R.id.tv_content)
    private val tvTime: TextView = itemView.findViewById(R.id.tv_time)
    private val tvTimeComplete: TextView = itemView.findViewById(R.id.tv_time_complete)
    private val tvLevelTag: TextView = itemView.findViewById(R.id.tv_level_tag)
    private val tvLevelReward: TextView = itemView.findViewById(R.id.tv_level_reward)
    private val btnComplete: TextView = itemView.findViewById(R.id.btn_complete)

    fun bindData(data: NormalTaskItemBean) {
        tvTitle.text = data.normalTask.baseTask.title
        tvTime.text = "创建时间:${DateUtils.getDateChinese(data.createTime)}"
        tvTimeComplete.text = "完成时间:${DateUtils.getDateChinese(data.normalTask.baseTask.completeTime)}"
        tvContent.text = data.normalTask.baseTask.content
        tvLevelTag.setTextColor(data.normalTask.normalReward.tagColor)
        tvLevelTag.text = data.normalTask.normalReward.levelTag
        tvLevelReward.text = "奖励：${data.normalTask.normalReward.exp}"
        btnComplete.setOnClickListener {
            click.invoke(data)
        }
    }
}