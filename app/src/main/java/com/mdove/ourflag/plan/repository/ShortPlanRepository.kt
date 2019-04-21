package com.mdove.ourflag.plan.repository

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import com.mdove.ourflag.room.dao.ShortPlanDao
import com.mdove.ourflag.room.table.ShortPlanBean

/**
 * Created by MDOve on 2019/4/21.
 */
class ShortPlanRepository(private val shortPlanDao: ShortPlanDao) {
    val allShortPlan: LiveData<List<ShortPlanBean>> = shortPlanDao.getAllShortPlanBean()

    @WorkerThread
    suspend fun insertShortPlan(shortPlanBean: ShortPlanBean) {
        shortPlanDao.insert(shortPlanBean)
    }
}