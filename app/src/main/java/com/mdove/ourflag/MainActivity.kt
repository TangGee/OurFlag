package com.mdove.ourflag

import androidx.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.mdove.ourflag.base.AbsActivity
import com.mdove.ourflag.plan.ShortPlan
import com.mdove.ourflag.plan.viewmodel.ShortPlanViewModel
import com.mdove.ourflag.room.table.ShortPlanBean
import com.mdove.android.base.toast.ToastUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbsActivity() {
    private lateinit var viewModel: ShortPlanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ShortPlanViewModel::class.java)

        btn_test.setOnClickListener {
            viewModel.insertShortPlan(ShortPlanBean(createTime = System.currentTimeMillis(),
                    plan = ShortPlan("111", "222")))
        }
        viewModel.shortPlanBeans.observe(this, Observer {
            it?.let {
                ToastUtil.toast("data${it.size}", Toast.LENGTH_SHORT)
            }
        })
    }
}
