package com.example.responsi1mobileh1d023077

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.responsi1mobileh1d023077.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
    }

    private fun initLayout() {
        binding.clubHistory.let {
            it.imgIcon.setImageResource(R.drawable.football)
            it.tvLayout.setText(R.string.club_history)
        }

        binding.headCoach.let {
            it.imgIcon.setImageResource(R.drawable.coach)
            it.tvLayout.setText(R.string.head_coach)
        }
        binding.teamSquad.let {
            it.imgIcon.setImageResource(R.drawable.team)
            it.tvLayout.setText(R.string.team_squad)
        }
    }

}