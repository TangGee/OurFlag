package com.mdove.ourflag.plan.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mdove.ourflag.R
import com.mdove.ourflag.plan.bean.NormalTaskItemBean
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by MDove on 2019/4/22.
 */
class NoDoneTaskNormalItemBinder : ItemViewBinder<NormalTaskItemBean, NoDoneTaskNormalItemViewHolder>() {
    override fun onBindViewHolder(viewHolder: NoDoneTaskNormalItemViewHolder, data: NormalTaskItemBean) {
        viewHolder.bindData(data)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): NoDoneTaskNormalItemViewHolder {
        return NoDoneTaskNormalItemViewHolder(inflater, parent)
    }
}

class NoDoneTaskNormalItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
        inflater.inflate(R.layout.item_no_done_task_normal, parent, false)) {
    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val tvContent: TextView = itemView.findViewById(R.id.tv_content)
    private val tvTime: TextView = itemView.findViewById(R.id.tv_time)
    private val tvLevel: TextView = itemView.findViewById(R.id.tv_level)

    fun bindData(data: NormalTaskItemBean) {
        tvTitle.text = data.normalTask.baseTask.title
        tvTime.text = data.createTime.toString()
        tvContent.text = data.normalTask.baseTask.content
        tvLevel.text = data.normalTask.baseDifficulty.levelTag
    }
}