package com.capstone.trashsortapp.ui.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashsortapp.R
import com.capstone.trashsortapp.data.Place

class TrashDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trash_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val place = intent.getParcelableExtra<Place>("key_place")
        if (place != null) {
            val image: ImageView = findViewById(R.id.iv_Trash)
            val name: TextView = findViewById(R.id.description_tittle)
            val description: TextView = findViewById(R.id.description)
            val classes: TextView = findViewById(R.id.tv_classes)

            image.setImageResource(place.placeImages)
            name.text = place.placeName
            description.text = place.placeDescription
            classes.text = place.placeClasses
        }

    }

}