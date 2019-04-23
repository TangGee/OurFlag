package com.mdove.ourflag.room.dao

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mdove.ourflag.room.table.NormalRewardBean
import com.mdove.ourflag.room.table.NormalTaskBean
import com.mdove.ourflag.room.table.ShortPlanBean

@Dao
interface NormalRewardDao {
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: NormalRewardBean): Long?

    @Query("SELECT * FROM normal_reward_table")
    fun getAll(): LiveData<NormalRewardBean>

    @Query("SELECT * FROM normal_reward_table WHERE id = :id")
    fun getNormalTaskBean(id: Long): LiveData<NormalRewardBean>

    @Query("DELETE FROM normal_reward_table")
    fun deleteAll()
}