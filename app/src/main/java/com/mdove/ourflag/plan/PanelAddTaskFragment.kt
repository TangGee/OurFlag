package com.mdove.ourflag.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mdove.android.base.init.getViewFromPool
import com.mdove.android.base.utils.DateUtils
import com.mdove.ourflag.R
import com.mdove.ourflag.plan.bean.BaseTask
import com.mdove.ourflag.plan.bean.NormalTask
import com.mdove.ourflag.plan.depends.ITaskPanelDepends
import com.mdove.ourflag.plan.viewmodel.NormalTaskViewModel
import com.mdove.ourflag.room.table.NormalTaskBean
import kotlinx.android.synthetic.main.fragment_panel_add_task.*

/**
 * Created by MDove on 2019/4/22.
 */
class PanelAddTaskFragment : Fragment() {
    private lateinit var mNormalViewModel: NormalTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            mNormalViewModel = ViewModelProviders.of(it).get(NormalTaskViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getViewFromPool(R.layout.fragment_panel_add_task, container, context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_add.setOnClickListener {
            mNormalViewModel.insertNormalTask(NormalTaskBean(normalTask = NormalTask(baseTask = BaseTask("Test", et_title.text.toString(), et_content.text.toString(), completeTime = DateUtils.getTimes(24, 20, 0)))))
        }

        drag_root.exitInvoke = {
            (activity as? ITaskPanelDepends)?.let {
                it.popTopFragment()
            }
        }
    }
}