package com.example.responsi1mobileh1d023077

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.responsi1mobileh1d023077.databinding.ActivityHistoryBinding
import com.example.responsi1mobileh1d023077.databinding.ActivityMainBinding

class HistoryActivity : ComponentActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}