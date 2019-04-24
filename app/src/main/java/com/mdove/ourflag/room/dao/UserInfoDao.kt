package com.mdove.ourflag.room.dao

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mdove.ourflag.room.table.UserInfoBean

@Dao
interface UserInfoDao {
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(state: UserInfoBean): Long?

    @Query("SELECT * FROM user_info_table LIMIT 0")
    fun getUserInfo(): LiveData<UserInfoBean>

    @Query("DELETE FROM user_info_table")
    fun deleteUserInfo()
}