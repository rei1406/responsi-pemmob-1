package com.example.responsi1mobileh1d023077.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.responsi1mobileh1d023077.databinding.ActivityHistoryBinding

class HistoryActivity : ComponentActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}