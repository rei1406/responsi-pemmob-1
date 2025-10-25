package com.example.responsi1mobileh1d023077

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.responsi1mobileh1d023077.databinding.ActivityMainBinding
import com.example.responsi1mobileh1d023077.ui.activity.CoachActivity
import com.example.responsi1mobileh1d023077.ui.activity.HistoryActivity
import com.example.responsi1mobileh1d023077.ui.activity.SquadActivity

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
        initListener()
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

    private fun initListener() {

        binding.clubHistory.root.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        binding.headCoach.root.setOnClickListener {
            startActivity(Intent(this, CoachActivity::class.java))
        }

        binding.teamSquad.root.setOnClickListener {
            startActivity(Intent(this, SquadActivity::class.java))
        }

    }


}