package com.mdove.android.service.arch

import java.util.concurrent.Executor

interface AppExecutors {
    val diskIO: Executor
    val networkIO: Executor
    val mainThread: Executor
}