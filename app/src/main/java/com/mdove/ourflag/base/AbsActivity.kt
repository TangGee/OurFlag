package com.mdove.ourflag.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mdove.android.base.coroutines.JobHandler
import com.mdove.android.uilib.utils.StatusBarUtils
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import com.mdove.ourflag.R

/**
 * Created by MDove on 2019/4/21.
 */
open class AbsActivity :AppCompatActivity() , JobHandler {
    override val job: Job
        get() = SupervisorJob()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        StatusBarUtils.setColorDiff(this, ContextCompat.getColor(this, R.color.colorPrimary))
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}