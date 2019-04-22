package com.mdove.ourflag.plan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mdove.ourflag.plan.repository.ShortPlanRepository
import com.mdove.ourflag.room.OurFlagDatabase
import com.mdove.ourflag.room.table.ShortPlanBean
import com.mdove.android.base.coroutines.contextJob
import com.mdove.android.base.network.threadpool.FastMain
import com.mdove.android.base.network.threadpool.MDoveCommonPool
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by MDove on 2019/4/21.
 */
class ShortPlanViewModel(application: Application) : AndroidViewModel(application) {
    private val scope = CoroutineScope(application.contextJob + FastMain)

    private val repository: ShortPlanRepository

    val shortPlanBeans: LiveData<List<ShortPlanBean>>

    init {
        val wordsDao = OurFlagDatabase.getDatabase(application).shortPlanDao()
        repository = ShortPlanRepository(wordsDao)
        shortPlanBeans = repository.allShortPlan
    }

    fun insertShortPlan(shortPlan: ShortPlanBean) = scope.launch(MDoveCommonPool) {
        repository.insertShortPlan(shortPlan)
    }
}