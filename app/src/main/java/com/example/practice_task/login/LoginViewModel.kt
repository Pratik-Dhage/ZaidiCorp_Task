package com.example.practice_task.login

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice_task.LoginResponse
import com.example.practice_task.api_manager.WebServices
import com.example.practice_task.helping_classes.Global
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {

    private lateinit var subscription: Disposable
    val mutLoginResponseApi: MutableLiveData<LoginResponse> = MutableLiveData()
    val mutErrorResponse: MutableLiveData<String> = MutableLiveData()


    // Login Api

    fun getLoginData(context: Activity) {
        subscription =
            Global.api_Service.LoginApi(WebServices.Domain + WebServices.verify_mobile_no)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe({ result -> onLoginApiSuccess(result) },
                    { error -> onApiError(error) })

    }


    private fun onLoginApiSuccess(result: LoginResponse) {
        mutLoginResponseApi.value = result
    }

    private fun onApiError(error: Throwable) {
        mutErrorResponse.value = error.localizedMessage
    }


}