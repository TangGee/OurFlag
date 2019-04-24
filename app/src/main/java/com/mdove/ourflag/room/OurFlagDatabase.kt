package com.mdove.ourflag.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mdove.ourflag.room.dao.*
import com.mdove.ourflag.room.table.*

@Database(entities = [ShortPlanBean::class, NormalTaskBean::class, NormalDifficultyBean::class, NormalRewardBean::class, UserInfoBean::class], version = 1)
abstract class OurFlagDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: OurFlagDatabase? = null

        fun getDatabase(context: Context): OurFlagDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        OurFlagDatabase::class.java,
                        "OurFlag_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun shortPlanDao(): ShortPlanDao
    abstract fun userInfoDao(): UserInfoDao
    abstract fun normalTaskDao(): NormalTaskDao
    abstract fun normalRewardDao(): NormalRewardDao
    abstract fun normalDifficultyDao(): NormalDifficultyDao
}