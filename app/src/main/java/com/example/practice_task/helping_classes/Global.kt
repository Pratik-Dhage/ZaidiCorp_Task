package com.example.practice_task.helping_classes

import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.practice_task.api_manager.RestClient
import com.google.android.material.snackbar.Snackbar

object Global {

    val api_Service by lazy {
        RestClient.create()
    }

    fun showToast(context: Context, str: String) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }

    fun showSnackBar(view: View, str: String) {
        val snackBar: Snackbar = Snackbar.make(view, str, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }
}