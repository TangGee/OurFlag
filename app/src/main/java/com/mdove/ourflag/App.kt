package com.mdove.ourflag

import com.mdove.android.base.AbsApplication
import com.mdove.android.base.coroutines.contextJob
import com.mdove.android.base.network.threadpool.MDoveBackgroundPool
import com.mdove.ourflag.base.bean.UserInfo
import com.mdove.ourflag.config.AppSpConfig
import com.mdove.ourflag.room.OurFlagDatabase
import com.mdove.ourflag.room.table.UserInfoBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
            AppSpConfig.setHasLogin()
            CoroutineScope(MDoveBackgroundPool).launch {
                AppSpConfig.setHasLogin()
                OurFlagDatabase.getDatabase(this@App).userInfoDao().insert(UserInfoBean(userInfo = UserInfo(autonomy = 0, exp = 0)))
            }
        }
    }
}