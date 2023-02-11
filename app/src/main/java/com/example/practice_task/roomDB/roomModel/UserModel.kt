package com.example.practice_task.roomDB.roomModel

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class UserModel(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="customerId")
    val customerId : String,
    @ColumnInfo(name ="customerName")
    val customerName : String ="",
    @ColumnInfo(name ="mobileNumber")
    val mobileNumber : String ="",
    @ColumnInfo(name ="emailId")
    val emailId : String ="",
)