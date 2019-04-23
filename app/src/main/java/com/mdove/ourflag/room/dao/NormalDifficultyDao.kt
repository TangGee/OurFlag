package com.mdove.ourflag.room.dao

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mdove.ourflag.room.table.NormalDifficultyBean
import com.mdove.ourflag.room.table.NormalRewardBean
import com.mdove.ourflag.room.table.NormalTaskBean
import com.mdove.ourflag.room.table.ShortPlanBean

@Dao
interface NormalDifficultyDao {
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: NormalDifficultyBean): Long?

    @Query("SELECT * FROM normal_difficulty_table")
    fun getAll(): LiveData<NormalDifficultyBean>

    @Query("SELECT * FROM normal_difficulty_table WHERE id = :id")
    fun getNormalTaskBean(id: Long): LiveData<NormalDifficultyBean>

    @Query("DELETE FROM normal_difficulty_table")
    fun deleteAll()
}