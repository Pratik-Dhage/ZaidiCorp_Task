package com.example.practice_task.login

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.practice_task.R
import com.example.practice_task.databinding.ActivityLoginBinding
import com.example.practice_task.databinding.ActivityMainBinding
import com.example.practice_task.helping_classes.Global
import com.example.practice_task.helping_classes.NetworkUtilities
import com.example.practice_task.roomDB.roomDatabase.UserDB
import com.example.practice_task.roomDB.roomModel.UserModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var view: View
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializeFields()
        if(NetworkUtilities.getConnectivityStatus(this)){
            initObserver()
        }
       // initObserver()
        else{
            useOfflineUserData();
        }
        callLoginApi()
        onClickListner()
    }

    private fun useOfflineUserData() {
        val mobileNumber = intent.getStringExtra("mobile_number").toString()
        val userDao = UserDB.getInstance(this).userDao()

        binding.txtCustomerID.text = userDao.getUserCustomerID(mobileNumber)
        binding.txtName.text = userDao.getUserName(mobileNumber)
        binding.txtMobileNumber.text = userDao.getUserMobileNumber(mobileNumber)
        binding.txtEmailID.text = userDao.getUserEmailID(mobileNumber)
    }

    private fun initObserver() {
        viewModel.mutLoginResponseApi.observe(this) {

            if (it.Status == 200) {

                it.Data.forEach {
                    val number =    it.personalcontact

                    if(intent.getStringExtra("mobile_number").toString()==number.toString()){

                        binding.txtName.text = it.customername
                        binding.txtCustomerID.text = it.customerid.toString()
                        binding.txtEmailID.text = it.emailid
                        binding.txtMobileNumber.text = it.personalcontact.toString()

                        //store Api Response in Room DB
                      //  storeInRoomDB(it.customerid.toString(),it.customername,it.personalcontact.toString(),it.emailid)

                    }
                    else{
                        Global.showToast(this,resources.getString(R.string.user_not_exits))
                    }
                }
            }


        }
    }

    private fun storeInRoomDB(customerid: String, customername: String,  personalcontact: String,emailid: String) {

        val userDao = UserDB.getInstance(this).userDao()
        val userData = UserModel(customerid,customername,personalcontact,emailid)
        userDao.insert(userData)

        Global.showToast(this,resources.getString(R.string.successfully_stored_in_roomDB))
      // Global.showToast(this,userDao.getRowCount())
    }

    private fun initializeFields() {
        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        view = binding.root //for snackBar
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.viewModel = viewModel

        val currentNightMode = AppCompatDelegate.getDefaultNightMode()
        if (currentNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            window.decorView.setBackgroundColor(Color.WHITE)
        } else {
            window.decorView.setBackgroundColor(Color.TRANSPARENT)
        }

    }

    private fun onClickListner(){
        binding.btnSave.setOnClickListener {

            //store Api Response in Room DB
           storeInRoomDB(binding.txtCustomerID.text.toString(),
               binding.txtName.text.toString(),binding.txtMobileNumber.text.toString(),
               binding.txtEmailID.text.toString())
        }
    }


    private fun callLoginApi() {
        if (NetworkUtilities.getConnectivityStatus(this)) {
            viewModel.getLoginData(this)
        } else {
            Global.showToast(this, resources.getString(R.string.no_internet))
        }
    }
}