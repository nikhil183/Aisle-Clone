package com.example.aisleassignment.adapter

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView

object BindingAdapters {
    @BindingAdapter("android:src")
    @JvmStatic
    fun setImageViewResource(imageView: ShapeableImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imageView)
    }
}
