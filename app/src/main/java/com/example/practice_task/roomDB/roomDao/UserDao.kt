package com.example.practice_task.roomDB.roomDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practice_task.roomDB.roomModel.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userModel : UserModel)

    @Query("Select Count(*) from userTable")
    fun getRowCount():String

    @Query("Select customerName from userTable where mobileNumber=:mobileNumber")
    fun getUserName(mobileNumber : String):String

    @Query("Select emailId from userTable where mobileNumber=:mobileNumber")
    fun getUserEmailID(mobileNumber : String):String

    @Query("Select customerId from userTable where mobileNumber=:mobileNumber")
    fun getUserCustomerID(mobileNumber : String):String

    @Query("Select mobileNumber from userTable where mobileNumber=:mobileNumber")
    fun getUserMobileNumber(mobileNumber : String):String



}