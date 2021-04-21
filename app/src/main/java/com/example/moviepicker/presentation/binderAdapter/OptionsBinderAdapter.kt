package com.example.moviepicker.presentation.binderAdapter

import android.content.SharedPreferences
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import com.example.moviepicker.presentation.viewmodel.OptionsViewModel
import com.google.android.material.imageview.ShapeableImageView
import java.util.*


class OptionsBinderAdapter {
    companion object {
        @BindingAdapter("isChecked")
        @JvmStatic
        fun changeMode(switchCompat: SwitchCompat, sharedPreferences: SharedPreferences) {
            val checked =
                sharedPreferences
                    .getBoolean(OptionsViewModel.darkMode, false)

            switchCompat.isChecked = checked
        }

        @BindingAdapter("usernameForInitials", "profile")
        @JvmStatic
        fun takeInitials(textView: TextView, usernameForInitials: String, profile: String) {
            if (profile != "") {
                textView.visibility = View.INVISIBLE
            } else {
                textView.visibility = View.VISIBLE
                textView.text = usernameForInitials.take(2).toUpperCase(Locale.ROOT)
            }
        }

        @BindingAdapter("profileImage")
        @JvmStatic
        fun showProfileImage(shapeDrawable: ShapeableImageView, profileImage: String) {
            if (profileImage != "") {
                val imageUri = Uri.parse(profileImage)
                shapeDrawable.setImageURI(imageUri)
                shapeDrawable.visibility = View.VISIBLE
            } else {
                shapeDrawable.visibility = View.INVISIBLE
            }
        }
    }
}