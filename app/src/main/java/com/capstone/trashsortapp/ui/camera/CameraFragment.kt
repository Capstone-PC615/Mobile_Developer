package com.capstone.trashsortapp.ui.camera

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.trashsortapp.R
import com.capstone.trashsortapp.repository.Classifier
import com.capstone.trashsortapp.ui.ResultActivity
import com.capstone.trashsortapp.ui.home.HomeFragment.Companion.EXTRA_TITLE
import java.io.ByteArrayOutputStream

class CameraFragment : Fragment() {
    private lateinit var viewModel: CameraViewModel
    private lateinit var classifier: Classifier

    private var imageUri: Uri? = null
    private val mInputSize = 224
    private val mModelPath = "TrashSort.tflite"
    private val mLabelPath = "label.txt"

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 100
        private const val GALLERY_PERMISSION_REQUEST_CODE = 200
        private const val CAMERA_REQUEST_CODE = 101
        private const val GALLERY_REQUEST_CODE = 201
    }

    private fun initClassifier() {
        classifier = Classifier(requireActivity().assets, mModelPath, mLabelPath, mInputSize)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initClassifier()
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CameraViewModel::class.java)

        val cameraButton = view.findViewById<Button>(R.id.cameraButton)
        val galleryButton = view.findViewById<Button>(R.id.galleryButton)
        val previewImageView = view.findViewById<ImageView>(R.id.previewImageView)
        val uploadButton = view.findViewById<Button>(R.id.uploadButton)

        cameraButton.setOnClickListener {
            checkCameraPermission()
        }

        galleryButton.setOnClickListener {
            checkGalleryPermission()
        }

        uploadButton.setOnClickListener {
            val imageBitmap = previewImageView.drawable?.toBitmap()
            if (imageBitmap != null) {
                val result = classifier.recognizeImage(imageBitmap)
                if (result.isEmpty()){
                    Toast.makeText(
                        activity,
                        "Gambar tidak dapat terdeteksi",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    val intent = Intent(activity, ResultActivity::class.java)
                    intent.putExtra(EXTRA_TITLE, result[0].title)
                    intent.putExtra("gambar", imageUri)
                    startActivity(intent)
                    Toast.makeText(requireContext(), result[0].title, Toast.LENGTH_SHORT).show()
                }

                val base64Image = convertBitmapToBase64(imageBitmap)
                viewModel.uploadImage(base64Image)
            }
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            openCamera()
        }
    }

    private fun checkGalleryPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                GALLERY_PERMISSION_REQUEST_CODE
            )
        } else {
            openGallery()
        }
    }

    private fun openCamera() {
        val value = ContentValues()
        value.put(MediaStore.Images.Media.TITLE, "New Picture")
        value.put(MediaStore.Images.Media.DESCRIPTION, "From the camera")
        imageUri = activity!!.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            value
        )
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    val mBitmap = BitmapFactory.decodeStream(
                        activity!!.contentResolver.openInputStream(imageUri!!))
//                    imageUri = data!!.data
                    println("$imageUri test")
                    view?.findViewById<ImageView>(R.id.previewImageView)!!.setImageBitmap(mBitmap)
                }
                GALLERY_REQUEST_CODE -> {
                    imageUri = data?.data
                    view?.findViewById<ImageView>(R.id.previewImageView)!!.setImageURI(imageUri)
                }
            }
        }
    }

    private fun convertBitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}




