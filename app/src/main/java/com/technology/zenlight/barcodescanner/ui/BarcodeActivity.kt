package com.technology.zenlight.barcodescanner.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.technologies.zenlight.barcodescanner.R
import com.technologies.zenlight.barcodescanner.databinding.BarcodeLayoutBinding
import com.technology.zenlight.barcodescanner.utils.BARCODE_EXTRA
import com.technology.zenlight.barcodescanner.utils.helpers.CameraHelper
import java.util.concurrent.Executors

class BarcodeActivity : AppCompatActivity() {

    private lateinit var binding: BarcodeLayoutBinding
    private lateinit var options: FirebaseVisionBarcodeDetectorOptions
    private lateinit var detector: FirebaseVisionBarcodeDetector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.barcode_layout)


        //todo Step One: Initialize variables
        options = FirebaseVisionBarcodeDetectorOptions.Builder()
            .setBarcodeFormats(
                FirebaseVisionBarcode.FORMAT_QR_CODE,
                FirebaseVisionBarcode.FORMAT_AZTEC
            )
            .build()

        detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options)

        //todo Step 7: Start Image Analysis
        binding.previewView.post { startImageAnalysis() }

    }


    /**
     * Creates our Preview and ImageAnalyser
     */
    private fun bindPreview(cameraProvider: ProcessCameraProvider) {
        //todo Step Two: Set up the preview view

        val executor = Executors.newSingleThreadExecutor()

        val displayMetrics =
            DisplayMetrics().also { binding.previewView.display.getRealMetrics(it) }

        val screenAspectRatio =
            CameraHelper.getAspectRatio(displayMetrics.widthPixels, displayMetrics.heightPixels)

        val rotation = binding.previewView.display.rotation

        val cameraSelector =
            CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

        val preview = Preview.Builder()
            .setTargetAspectRatio(screenAspectRatio)
            .setTargetRotation(rotation)
            .build()

        val imageAnalysis = ImageAnalysis.Builder()
            .setTargetAspectRatio(screenAspectRatio)//is this needed
            .setTargetRotation(rotation)
            .setImageQueueDepth(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()

        imageAnalysis.setAnalyzer(executor, MachineLearningAnalyzer())

        cameraProvider.unbindAll()

        val camera = cameraProvider.bindToLifecycle(
            this,
            cameraSelector,
            imageAnalysis,
            preview)

        camera.cameraControl.enableTorch(true)
        preview.setSurfaceProvider(binding.previewView.createSurfaceProvider(camera.cameraInfo))
    }


    private fun startImageAnalysis() {
        //todo Step Three: Create our Camera Provider
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(this))
    }


    private inner class MachineLearningAnalyzer : ImageAnalysis.Analyzer {
        private var lastAnalyzedTimestamp = 0L
        private var resultsFound = false

        @SuppressLint("UnsafeExperimentalUsageError")
        override fun analyze(imageProxy: ImageProxy) {

            //todo Step Four: Analyze image

            if (hasHalfSecondPassed()) {
                val mediaImage = imageProxy.image
                val degrees = imageProxy.imageInfo.rotationDegrees
                val imageRotation = degreesToFirebaseRotation(degrees)
                mediaImage?.let {
                    val image = FirebaseVisionImage.fromMediaImage(mediaImage, imageRotation)
                    detector.detectInImage(image)
                        .addOnSuccessListener { barcodes ->
                            interpretResults(barcodes)
                        }
                        .addOnFailureListener {
                            Log.d(TAG, it.localizedMessage ?: "")
                        }
                }
            }

            //todo Step Five: Close the imageProxy
            imageProxy.close()
        }

        private fun interpretResults(barcodeList: List<FirebaseVisionBarcode>) {
            for (barcode in barcodeList) {
                val rawValue = barcode.rawValue
                onBarcodeObserved(rawValue)
                break
            }
        }

        private fun degreesToFirebaseRotation(degrees: Int): Int = when (degrees) {
            0 -> FirebaseVisionImageMetadata.ROTATION_0
            90 -> FirebaseVisionImageMetadata.ROTATION_90
            180 -> FirebaseVisionImageMetadata.ROTATION_180
            270 -> FirebaseVisionImageMetadata.ROTATION_270
            else -> throw Exception("Rotation must be 0, 90, 180, or 270.")
        }

        private fun onBarcodeObserved(barcode: String?) {
            //the resultsFound boolean is used here so we don't get this called
            //called twice in the span of 1 second

            //todo Step 6: Interpret results

            if (!barcode.isNullOrEmpty() && !resultsFound) {
                resultsFound = true
                val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= 26) {
                    v.vibrate(VibrationEffect.createOneShot(250, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    v.vibrate(250)
                }
                val intent = Intent()
                intent.putExtra(BARCODE_EXTRA, barcode)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        private fun hasHalfSecondPassed(): Boolean {
            val currentTimestamp = System.currentTimeMillis()
            return if (currentTimestamp - lastAnalyzedTimestamp >= 500) {
                lastAnalyzedTimestamp = currentTimestamp
                true
            } else {
                false
            }
        }
    }

    companion object {
        private val TAG = BarcodeActivity::class.java.simpleName
        fun newIntent(context: Context) = Intent(context, BarcodeActivity::class.java)
    }
}