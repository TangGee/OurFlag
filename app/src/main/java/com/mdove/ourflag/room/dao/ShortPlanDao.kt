package com.mdove.ourflag.room.dao

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mdove.ourflag.room.table.ShortPlanBean

@Dao
interface ShortPlanDao {
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(state: ShortPlanBean): Long?

    @Query("SELECT * FROM short_plan_table WHERE id = :id")
    fun getShortPlanBean(id: Long): LiveData<ShortPlanBean>

    @Query("SELECT * FROM short_plan_table ORDER BY createTime ASC")
    fun getAllShortPlanBean(): LiveData<List<ShortPlanBean>>

    @Query("DELETE FROM short_plan_table")
    fun deleteAll()
}