package com.mdove.ourflag

import com.mdove.android.base.AbsApplication
import com.mdove.ourflag.config.AppSpConfig
import com.mdove.ourflag.room.OurFlagDatabase
import com.mdove.ourflag.room.table.NormalRewardBean

/**
 * Created by MDove on 2019/4/21.
 */
class App : AbsApplication() {

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    private fun initData() {
        if (!AppSpConfig.isHasLogin()) {
        }
    }
}