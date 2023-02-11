package com.example.practice_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.practice_task.databinding.ActivityMainBinding
import com.example.practice_task.helping_classes.Global
import com.example.practice_task.helping_classes.NetworkUtilities
import com.example.practice_task.login.LoginActivity
import com.example.practice_task.login.LoginViewModel
import java.util.regex.Pattern

// Login Screen
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var view: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeFields()
        onClickListeners()

    }

    private fun initializeFields() {
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        view = binding.root //for snackBar

    }


    private fun onClickListeners() {
      binding.btnVerify.setOnClickListener{


          if(validation()) {
               val i = Intent(this,LoginActivity::class.java)
              val mobileNumber =binding.edtMobile.text.toString().trim()
              i.putExtra("mobile_number",mobileNumber)
              startActivity(i)

          }


      }
    }


    private fun validation(): Boolean {

        if(binding.edtMobile.text.toString().isEmpty()){
            Global.showToast(this,resources.getString(R.string.mobile_number_cannot_be_empty))
            return false
        }


        if (!Pattern.matches("^[1-9][0-9]{9}$", binding.edtMobile.text.toString())) {
            Global.showToast(this, resources.getString(R.string.please_enter_proper_number))
            return false
        }


        return true
    }


}