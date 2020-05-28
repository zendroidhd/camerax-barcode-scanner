package com.technologies.zenlight.daggerinmodule.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.technologies.zenlight.daggerinmodule.AlertHelper
import com.technologies.zenlight.daggerinmodule.R
import com.technologies.zenlight.daggerinmodule.databinding.FragmentContainerBinding
import com.technologies.zenlight.daggerinmodule.dependencyInjection.DaggerInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appContext: Context

    @Inject
    lateinit var alertHelper: AlertHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerInjector.getComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        val binding: FragmentContainerBinding = DataBindingUtil.setContentView(this, R.layout.fragment_container)

        binding.btnActivity.setOnClickListener {
            Toast.makeText(appContext, "Activity successful", Toast.LENGTH_LONG).show()
        }

        binding.btnConstructor.setOnClickListener {
            alertHelper.showTestAlert()
        }
    }

}