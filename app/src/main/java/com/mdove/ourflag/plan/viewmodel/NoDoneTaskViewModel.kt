package com.mdove.ourflag.plan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mdove.android.base.coroutines.contextJob
import com.mdove.android.base.network.threadpool.FastMain
import com.mdove.android.base.network.threadpool.MDoveCommonPool
import com.mdove.ourflag.plan.bean.BaseTaskListItem
import com.mdove.ourflag.plan.repository.NoDoneTaskRepository
import com.mdove.ourflag.room.OurFlagDatabase
import com.mdove.ourflag.room.table.NormalTaskBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by MDove on 2019/4/22.
 */
class NoDoneTaskViewModel(application: Application) : AndroidViewModel(application) {
    private val scope = CoroutineScope(application.contextJob + FastMain)

    private val repository: NoDoneTaskRepository

    val normalTaskItemBeans: LiveData<List<BaseTaskListItem>>

    init {
        val dao = OurFlagDatabase.getDatabase(application).normalTaskDao()
        repository = NoDoneTaskRepository(dao)
        normalTaskItemBeans = repository.allNoDoneTask
    }

    fun insertNormalTask(normalTask: NormalTaskBean) = scope.launch(MDoveCommonPool) {
        repository.insertNormalTask(normalTask)
    }
}