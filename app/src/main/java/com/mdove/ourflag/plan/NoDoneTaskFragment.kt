package com.mdove.ourflag.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mdove.android.base.init.getViewFromPool
import com.mdove.android.base.utils.assemble
import com.mdove.ourflag.R
import com.mdove.ourflag.plan.adapter.NoDoneTaskAdapter
import com.mdove.ourflag.plan.bean.BaseTask
import com.mdove.ourflag.plan.bean.NormalTask
import com.mdove.ourflag.plan.viewmodel.NoDoneTaskViewModel
import com.mdove.ourflag.room.table.NormalTaskBean
import kotlinx.android.synthetic.main.fragment_no_done_task.*

/**
 * Created by MDove on 2019/4/22.
 */
class NoDoneTaskFragment : Fragment() {
    private lateinit var viewModel: NoDoneTaskViewModel
    private lateinit var adapter: NoDoneTaskAdapter

    companion object {
        fun instance(bundle: Bundle?): NoDoneTaskFragment {
            val fragment = NoDoneTaskFragment()
            fragment.assemble(bundle)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(NoDoneTaskViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getViewFromPool(R.layout.fragment_no_done_task, container, context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NoDoneTaskAdapter {
            val time=java.lang.Long.toHexString(System.currentTimeMillis())
            viewModel.insertNormalTask(NormalTaskBean(normalTask = NormalTask(baseTask = BaseTask("哈哈$time", "呵呵$time", "嘿嘿$time"))))
        }
        rlv.layoutManager = LinearLayoutManager(activity)
        rlv.adapter = adapter
        viewModel.normalTaskItemBeans.observe(this, Observer { data ->
            adapter.updateData(data)
        })
    }
}