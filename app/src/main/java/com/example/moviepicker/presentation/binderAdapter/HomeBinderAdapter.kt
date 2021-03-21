package com.example.moviepicker.presentation.binderAdapter

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.moviepicker.presentation.dialog.CreateGroupAlertDialog

class HomeBinderAdapter {
    companion object {
        @BindingAdapter("fragmentManager")
        @JvmStatic
        fun createWatchParty(linearLayout: LinearLayout, fragmentManager: FragmentManager) {
            linearLayout.setOnClickListener {
                val createGroupDialogFragment: DialogFragment = CreateGroupAlertDialog()
                createGroupDialogFragment.show(fragmentManager, "createWatchParty")
            }
        }
    }
}