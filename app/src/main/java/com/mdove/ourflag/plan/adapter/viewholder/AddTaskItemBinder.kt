package com.mdove.ourflag.plan.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mdove.android.uilib.utils.setDebounceOnClickListener
import com.mdove.ourflag.R
import com.mdove.ourflag.plan.bean.AddTaskItemBean
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by MDove on 2019/4/22.
 */
class AddTaskItemBinder(private val click: () -> Unit) : ItemViewBinder<AddTaskItemBean, AddTaskItemViewHolder>() {
    override fun onBindViewHolder(holder: AddTaskItemViewHolder, data: AddTaskItemBean) {
        holder.itemView.setDebounceOnClickListener {
            click.invoke()
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = AddTaskItemViewHolder(inflater, parent)
}

class AddTaskItemViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_add_task, parent, false))
