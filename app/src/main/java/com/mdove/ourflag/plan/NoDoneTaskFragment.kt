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
import com.mdove.ourflag.plan.bean.toNormalTaskBean
import com.mdove.ourflag.plan.viewmodel.NoDoneTaskViewModel
import kotlinx.android.synthetic.main.fragment_no_done_task.*

/**
 * Created by MDove on 2019/4/22.
 */
class NoDoneTaskFragment : Fragment() {
    private lateinit var viewModel: NoDoneTaskViewModel
    private lateinit var adapter: NoDoneTaskAdapter

    companion object {
        const val ADD_TASK_FRAGMENT_TAG = "add_task_fragment_tag"

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
        adapter = NoDoneTaskAdapter({ startAddTask() }, { taskItem ->
            viewModel.completeTask(taskItem.toNormalTaskBean())
        })
        rlv.layoutManager = LinearLayoutManager(activity)
        rlv.adapter = adapter
        viewModel.normalTaskItemBeans.observe(this, Observer { data ->
            adapter.updateData(data)
        })
//        viewModel.completeStatus.observe(this, Observer {
//            ToastUtil.toast(it.toast, Toast.LENGTH_SHORT)
//        })
    }

    private fun startAddTask() {
        activity?.let {
            if (it.supportFragmentManager.findFragmentByTag(ADD_TASK_FRAGMENT_TAG) == null) {
                it.supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.dim_in, R.anim.dim_out, R.anim.dim_in, R.anim.dim_out)
                        .replace(
                                R.id.root_layout,
                                PanelAddTaskFragment(),
                                ADD_TASK_FRAGMENT_TAG
                        )
                        .addToBackStack(ADD_TASK_FRAGMENT_TAG)
                        .commitAllowingStateLoss()
            }
        }
    }

    private fun popAddTaskFragment() {
        activity?.let {
            it.supportFragmentManager.popBackStack()
        }
    }
}