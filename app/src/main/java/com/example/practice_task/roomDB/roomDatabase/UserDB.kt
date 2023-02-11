package com.example.practice_task.roomDB.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practice_task.roomDB.roomDao.UserDao
import com.example.practice_task.roomDB.roomModel.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class UserDB : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var database: UserDB? = null
        private val DATABASE_NAME = "USER_DATABASE"

        @Synchronized // this will allow only one instance throughout the app
        fun getInstance(context: Context): UserDB {

            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    UserDB::class.java,
                    DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database!!
        }
    }
}