package com.mdove.ourflag.utils.coroutines

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

interface JobHandler {
    val job: Job
}

val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    Log.e("JobHandler", "", throwable)
}

val Fragment.contextJob: CoroutineContext
    get() = ((this as? JobHandler)?.job ?: (context as? JobHandler)?.job
    ?: Dispatchers.Default) + exceptionHandler

val Activity.contextJob: CoroutineContext
    get() = ((this as? JobHandler)?.job ?: Dispatchers.Default) + exceptionHandler

val Context?.contextJob: CoroutineContext
    get() = ((this as? JobHandler)?.job ?: Dispatchers.Default) + exceptionHandler
