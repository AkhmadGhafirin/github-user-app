package com.cascer.githubuserapp.utils

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cascer.githubuserapp.R
import java.util.Random

object ImageUtils {
    fun ImageView.load(context: Context, url: String) {
        Glide.with(context)
            .load(url.trim())
            .placeholder(R.drawable.baseline_image_24)
            .into(this)
    }

    fun ImageView.loadCircle(context: Context, url: String) {
        Glide.with(context)
            .load(url.trim())
            .circleCrop()
            .placeholder(R.drawable.baseline_person_24)
            .into(this)
    }

    fun getRandomColor(): Int {
        val random = Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }
}