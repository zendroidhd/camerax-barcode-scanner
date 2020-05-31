package com.technology.zenlight.barcodescanner.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import com.technologies.zenlight.barcodescanner.R
import com.technologies.zenlight.barcodescanner.databinding.MainLayoutBinding
import com.technology.zenlight.barcodescanner.utils.BARCODE_EXTRA
import com.technology.zenlight.barcodescanner.utils.BARCODE_REQUEST_CODE
import com.technology.zenlight.barcodescanner.utils.REQUEST_CAMERA_PERMISSIONS
import com.technology.zenlight.barcodescanner.utils.helpers.AppPermissionsHelper

class MainActivity : AppCompatActivity() {

    lateinit var binding: MainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: MainLayoutBinding = DataBindingUtil.setContentView(this, R.layout.main_layout)

        binding.btnScan.setOnClickListener {
            if (AppPermissionsHelper.isCameraPermissionsGranted(this)) {
                val intent = BarcodeActivity.newIntent(this)
                startActivityForResult(intent, BARCODE_REQUEST_CODE)
                //  BarcodeHelper.startScanning(this)
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSIONS && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val intent = BarcodeActivity.newIntent(this)
            startActivityForResult(intent, BARCODE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == BARCODE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val barcode = data?.getStringExtra(BARCODE_EXTRA) ?: ""
            Toast.makeText(this, barcode, Toast.LENGTH_SHORT).show()
        }
    }

}