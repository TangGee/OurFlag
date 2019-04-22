package com.mdove.ourflag.room.dao

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mdove.ourflag.room.table.NormalTaskBean
import com.mdove.ourflag.room.table.ShortPlanBean

@Dao
interface NormalTaskDao {
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: NormalTaskBean): Long?

    @Query("SELECT * FROM normal_task_table WHERE id = :id")
    fun getNormalTaskBean(id: Long): LiveData<NormalTaskBean>

    @Query("SELECT * FROM normal_task_table WHERE done = 1 ORDER BY create_time ASC")
    fun getAllNoDoneNormalTaskBean(): LiveData<List<NormalTaskBean>>

    @Query("DELETE FROM short_plan_table")
    fun deleteAll()
}