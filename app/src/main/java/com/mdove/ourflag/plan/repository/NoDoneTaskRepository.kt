package com.mdove.ourflag.plan.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mdove.ourflag.plan.bean.AddTaskItemBean
import com.mdove.ourflag.plan.bean.BaseTaskListItem
import com.mdove.ourflag.plan.bean.NormalTaskItemBean
import com.mdove.ourflag.plan.bean.toNormalTaskItemBean
import com.mdove.ourflag.room.dao.NormalTaskDao
import com.mdove.ourflag.room.dao.ShortPlanDao
import com.mdove.ourflag.room.table.NormalTaskBean
import com.mdove.ourflag.room.table.ShortPlanBean

/**
 * Created by MDove on 2019/4/22.
 */
class NoDoneTaskRepository(private val normalTaskDao: NormalTaskDao) {
    val allNoDoneTask: LiveData<List<BaseTaskListItem>> = Transformations.switchMap(normalTaskDao.getAllNoDoneNormalTaskBean()) {
        val liveData = MutableLiveData<List<BaseTaskListItem>>()
        val data = mutableListOf<BaseTaskListItem>()
        data.add(0, AddTaskItemBean)
        it.map {first->
            data.add(first.toNormalTaskItemBean())
        }
        liveData.postValue(data)
        liveData
    }

    @WorkerThread
    suspend fun insertNormalTask(normalTaskBean: NormalTaskBean) {
        normalTaskDao.insert(normalTaskBean)
    }

    @WorkerThread
    suspend fun updateNormalTask(normalTaskBean: NormalTaskBean) {
        normalTaskDao.update(normalTaskBean)
    }
}