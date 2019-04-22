package com.mdove.ourflag

import android.app.Application
import com.mdove.android.base.toast.ToastUtil
import com.mdove.ourflag.service.AppExecutorsImpl
import com.mdove.android.service.MDoveServiceManager

/**
 * Created by MDove on 2019/4/21.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ToastUtil.init(this)

        MDoveServiceManager.appExcutors = AppExecutorsImpl.inst
    }
}