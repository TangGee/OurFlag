package com.mdove.service

import com.mdove.service.arch.AppExecutors
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by MDove on 2019/4/21.
 */
object MDoveServiceManager : IMDoveServiceManager {
    override var appExcutors: AppExecutors? =null
        get() {
            return field ?: object : AppExecutors {
                override val diskIO: Executor
                    get() = Executors.newSingleThreadExecutor()

                override val networkIO: Executor
                    get() = Executors.newSingleThreadExecutor()

                override val mainThread: Executor
                    get() = Executors.newSingleThreadExecutor()
            }
        }
}