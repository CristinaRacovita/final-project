package com.example.moviepicker.presentation.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.moviepicker.R
import com.example.moviepicker.presentation.viewmodel.GroupItemViewModel

class GroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        val users = intent.getSerializableExtra("selectedUsers") as List<GroupItemViewModel>?
        val groupName = intent.getStringExtra("groupName")

        val textView: TextView = findViewById(R.id.groupName_textView)
        textView.text = groupName

    }
}