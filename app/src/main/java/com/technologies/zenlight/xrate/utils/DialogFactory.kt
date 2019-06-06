package com.technologies.zenlight.xrate.utils

import android.app.Activity
import android.app.AlertDialog

fun showAlertDialog(activity: Activity?, title: String, message: String){
    activity?.let {
        if (!it.isFinishing && !it.isDestroyed){
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle(title)
                .setMessage(message)
                .setNegativeButton("OK") {_, _ -> }
            dialog.show()
        }
    }
}