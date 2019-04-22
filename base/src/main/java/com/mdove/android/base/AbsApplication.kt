package com.mdove.android.base

import android.app.Application

import com.mdove.android.base.toast.ToastUtil

/**
 * Created by MDove on 2019/4/22.
 */
abstract class AbsApplication : Application() {
    companion object {
        lateinit var inst: AbsApplication
            protected set
    }

    override fun onCreate() {
        super.onCreate()
        inst = this

        ToastUtil.init(this)
    }
}
