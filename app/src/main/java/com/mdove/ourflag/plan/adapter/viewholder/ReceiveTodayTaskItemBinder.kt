package com.mdove.ourflag.plan.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mdove.android.base.utils.DateUtils
import com.mdove.ourflag.R
import com.mdove.ourflag.plan.bean.NormalTaskItemBean
import com.mdove.ourflag.view.DifficultyLevelView
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by MDove on 2019/4/24.
 */
class ReceiveTodayTaskItemBinder(val click: (taskBean: NormalTaskItemBean) -> Unit) : ItemViewBinder<NormalTaskItemBean, ReceiveTodayTaskItemViewHolder>() {
    override fun onBindViewHolder(viewHolderTask: ReceiveTodayTaskItemViewHolder, data: NormalTaskItemBean) {
        viewHolderTask.bindData(data)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ReceiveTodayTaskItemViewHolder {
        return ReceiveTodayTaskItemViewHolder(inflater, parent, click)
    }
}

class ReceiveTodayTaskItemViewHolder(inflater: LayoutInflater, parent: ViewGroup, val click: (taskBean: NormalTaskItemBean) -> Unit) : RecyclerView.ViewHolder(
        inflater.inflate(R.layout.item_receive_today_task, parent, false)) {
    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val tvContent: TextView = itemView.findViewById(R.id.tv_content)
    private val tvTime: TextView = itemView.findViewById(R.id.tv_time)
    private val tvTimeComplete: TextView = itemView.findViewById(R.id.tv_time_complete)
    private val tvLevelTag: TextView = itemView.findViewById(R.id.tv_level_tag)
    private val tvLevelReward: TextView = itemView.findViewById(R.id.tv_level_reward)
    private val btnComplete: TextView = itemView.findViewById(R.id.btn_complete)
    private val layoutLevel: DifficultyLevelView = itemView.findViewById(R.id.layout_level)

    fun bindData(data: NormalTaskItemBean) {
        tvTitle.text = data.normalTask.baseTask.title
        layoutLevel.initBg(data.normalTask.normalReward.level)
        tvTime.text = "创建时间:${DateUtils.getDateChinese(data.createTime)}"
        tvTimeComplete.text = "完成时间:${DateUtils.getDateChinese(data.normalTask.baseTask.completeTime)}"
        tvContent.text = data.normalTask.baseTask.content
        tvLevelTag.setTextColor(data.normalTask.normalReward.tagColor)
        tvLevelTag.text = data.normalTask.normalReward.levelTag
        tvLevelReward.text = "奖励：${data.normalTask.normalReward.exp}"
        if (data.done == 0) {
            btnComplete.text = "已完成"
        } else {
            btnComplete.text = "完成"
        }
        btnComplete.setOnClickListener {
            click.invoke(data)
        }
    }
}