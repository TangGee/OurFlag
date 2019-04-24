package com.mdove.ourflag.room.dao

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*
import com.mdove.ourflag.room.table.NormalTaskBean
import com.mdove.ourflag.room.table.ShortPlanBean

@Dao
interface NormalTaskDao {
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: NormalTaskBean): Long?

    @Update
    fun update(value: NormalTaskBean)

    @Query("SELECT * FROM normal_task_table WHERE id = :id")
    fun getNormalTaskBean(id: Long): LiveData<NormalTaskBean>

    @Query("SELECT * FROM normal_task_table WHERE done = 1 ORDER BY create_time DESC")
    fun getAllNoDoneNormalTaskBean(): LiveData<List<NormalTaskBean>>

    @Query("DELETE FROM short_plan_table")
    fun deleteAll()
}