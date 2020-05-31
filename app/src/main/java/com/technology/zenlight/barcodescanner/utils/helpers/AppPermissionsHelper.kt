package com.technology.zenlight.barcodescanner.utils.helpers

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.technology.zenlight.barcodescanner.utils.REQUEST_CAMERA_PERMISSIONS

object AppPermissionsHelper {

    /********* Camera Permissions *********/

    fun isCameraPermissionsGranted(activity: Activity): Boolean {
        return if (ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSIONS)
            false
        } else {
            true
        }
    }
}