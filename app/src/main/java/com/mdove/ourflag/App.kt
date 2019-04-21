package com.mdove.ourflag

import android.app.Application
import com.mdove.ourflag.utils.toast.ToastUtil

/**
 * Created by MDove on 2019/4/21.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ToastUtil.init(this)
    }
}