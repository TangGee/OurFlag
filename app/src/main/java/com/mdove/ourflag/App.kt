package com.mdove.ourflag

import android.app.Application
import com.mdove.ourflag.utils.network.AppExecutorsImpl
import com.mdove.ourflag.utils.toast.ToastUtil
import com.mdove.service.MDoveServiceManager

/**
 * Created by MDove on 2019/4/21.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ToastUtil.init(this)

        MDoveServiceManager.appExcutors = AppExecutorsImpl()
    }
}