package com.mdove.ourflag.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mdove.android.base.init.getViewFromPool
import com.mdove.android.base.utils.assemble
import com.mdove.ourflag.R

/**
 * Created by MDove on 2019/4/22.
 */
class NoDoneTaskFragment : Fragment() {

    companion object {
        fun instance(bundle: Bundle?): NoDoneTaskFragment {
            val fragment = NoDoneTaskFragment()
            fragment.assemble(bundle)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getViewFromPool(R.layout.fragment_no_done_task, container, context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}