package com.mdove.ourflag.plan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mdove.android.base.coroutines.contextJob
import com.mdove.android.base.network.threadpool.FastMain
import com.mdove.android.base.network.threadpool.MDoveCommonPool
import com.mdove.ourflag.plan.bean.BaseTaskListItem
import com.mdove.ourflag.plan.bean.NormalReward
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
    val completeStatus: MediatorLiveData<CompleteStatus> = MediatorLiveData()

    init {
        val dao = OurFlagDatabase.getDatabase(application).normalTaskDao()
        repository = NoDoneTaskRepository(dao)
        normalTaskItemBeans = repository.allNoDoneTask
        completeStatus.value = CompleteStatus(TaskStatus.IDLE, null, "")
    }

    fun insertNormalTask(normalTask: NormalTaskBean) = scope.launch(MDoveCommonPool) {
        repository.insertNormalTask(normalTask)
    }

    fun completeTask(normalTask: NormalTaskBean) = scope.launch(MDoveCommonPool) {
        val curSystem = System.currentTimeMillis()
        if (curSystem < normalTask.normalTask.baseTask.completeTime) {
            normalTask.done = 0
            repository.updateNormalTask(normalTask)
            completeStatus.postValue(CompleteStatus(TaskStatus.SUC, normalTask.normalTask.normalReward, "完成"))
        } else {
            completeStatus.postValue(CompleteStatus(TaskStatus.FAIL, normalTask.normalTask.normalReward, "失败"))
        }
    }
}

data class CompleteStatus(val taskStatus: TaskStatus, val normalReward: NormalReward?, val toast: String, val isSuc: Boolean = taskStatus == TaskStatus.SUC)

enum class TaskStatus {
    SUC,
    FAIL,
    IDLE
}