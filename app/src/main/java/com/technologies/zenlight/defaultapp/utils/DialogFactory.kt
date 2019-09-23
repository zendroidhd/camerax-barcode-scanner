package com.technologies.zenlight.defaultapp.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun showAlertDialog(activity: Activity?, title: String, message: String, negativeBtnText:String = "OK"){
    activity?.let {
        if (!it.isFinishing && !it.isDestroyed){
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle(title)
                .setMessage(message)
                .setNegativeButton(negativeBtnText) {_, _ -> }
            dialog.show()
        }
    }
}

fun showAlertDialogOneChoice(activity: Activity?, title: String, message: String, negativeBtnText:String = "OK", onOkClicked: ()-> Unit, isCancellable: Boolean = false){
    activity?.let {
        if (!it.isFinishing && !it.isDestroyed){
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle(title)
                .setCancelable(isCancellable)
                .setMessage(message)
                .setNegativeButton(negativeBtnText) {_, _ -> onOkClicked()}
            dialog.show()
        }
    }
}

fun showAlertDialogTwoChoices(activity: Activity?,
                              title: String,
                              message: String,
                              negativeBtnText:String = "Cancel",
                              positviveBtnText:String = "Ok",
                              onOkClicked: ()-> Unit){
    activity?.let {
        if (!it.isFinishing && !it.isDestroyed){
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle(title)
                .setMessage(message)
                .setNegativeButton(negativeBtnText) {_, _ ->}
                .setPositiveButton(positviveBtnText) {_, _ -> onOkClicked()}
            dialog.show()
        }
    }
}

fun showForgotPasswordAlertDialog(activity: Activity?, onForgotPassword: ()-> Unit) {

    activity?.let {
        if (!it.isFinishing && !it.isDestroyed){
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle("Invalid Password")
                .setMessage("The password you entered does not match your account")
                .setNegativeButton("Try Again") {_, _ -> }
                .setPositiveButton("Forgot Password?") {_, _ ->
                    onForgotPassword()
                }
            dialog.show()
        }
    }
}


fun showPasswordResetAlertDialog(activity: Activity?, onPasswordReset: ()-> Unit) {

    activity?.let {
        if (!it.isFinishing && !it.isDestroyed){
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle("Password Reset")
                .setCancelable(false)
                .setMessage("A password reset link has been sent to your email")
                .setPositiveButton("OK") {_, _ ->
                    onPasswordReset()
                }
            dialog.show()
        }
    }
}

fun showEmailAlreadyInUseAlertDialog(activity: Activity?, onLoginSelected: ()-> Unit) {

    activity?.let {
        if (!it.isFinishing && !it.isDestroyed){
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle("Account Status")
                .setMessage("This email is already being used by another account, would you like to log into your account instead?")
                .setNegativeButton("Try Again") {_, _ -> }
                .setPositiveButton("Log In") {_, _ ->
                    onLoginSelected()
                }
            dialog.show()
        }
    }
}

fun showSignUpAlertDialog(activity: Activity?, onSignUpOpened: () -> Unit) {

    activity?.let {
        if (!it.isFinishing && !it.isDestroyed){
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle("Invalid Credentials")
                .setMessage("Your email could not be found")
                .setNegativeButton("Try Again") {_, _ -> }
                .setPositiveButton("Sign Up") {_, _ ->
                    onSignUpOpened()
                }
            dialog.show()
        }
    }
}

fun showSignOutAlertDialog(activity: Activity?, onSignOut: () -> Unit) {

    activity?.let {
        if (!it.isFinishing && !it.isDestroyed){
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle("Sign Out")
                .setMessage("Do you want to sign out of this account?")
                .setNegativeButton("No") {_, _ -> }
                .setPositiveButton("Yes") {_, _ ->
                    onSignOut()
                }
            dialog.show()
        }
    }
}

fun showSaveChangesAlertDialog(activity: Activity?, onSaveChanges: () -> Unit) {

    activity?.let {
        if (!it.isFinishing && !it.isDestroyed){
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle("Save Changes?")
                .setMessage("Do you want to save all changes?")
                .setNegativeButton("No") {_, _ -> }
                .setPositiveButton("Yes") {_, _ ->
                    onSaveChanges()
                }
            dialog.show()
        }
    }
}

fun showToastLong(context: Context?, msg: String) {
    context.let { Toast.makeText(it, msg, Toast.LENGTH_LONG).show() }
}

fun showToastShort(context: Context?, msg: String) {
    context.let { Toast.makeText(it, msg, Toast.LENGTH_SHORT).show() }
}