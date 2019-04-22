package com.mdove.ourflag.base

import androidx.appcompat.app.AppCompatActivity
import com.mdove.android.base.coroutines.JobHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

/**
 * Created by MDove on 2019/4/21.
 */
open class AbsActivity :AppCompatActivity() , JobHandler {
    override val job: Job
        get() = SupervisorJob()

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}