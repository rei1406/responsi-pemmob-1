package com.example.responsi1mobileh1d023077.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.responsi1mobileh1d023077.R
import com.example.responsi1mobileh1d023077.databinding.ActivityCoachBinding
import com.example.responsi1mobileh1d023077.ui.viewmodel.CoachUiState
import com.example.responsi1mobileh1d023077.ui.viewmodel.CoachViewModel
import kotlinx.coroutines.launch

class CoachActivity : ComponentActivity() {
    private lateinit var binding: ActivityCoachBinding
    private val viewModel: CoachViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        observeUiState()
    }
    
    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is CoachUiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.errorMessage.visibility = View.GONE
                        }
                        
                        is CoachUiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.errorMessage.visibility = View.GONE
                            
                            // Update UI with coach data
                            binding.coachName.text = state.coach.name
                            binding.coachDateOfBirth.text = "Date of Birth: ${state.formattedDateOfBirth}"
                            binding.coachNationality.text = "Nationality: ${state.coach.nationality}"
                            
                            // Load coach image
                            Glide.with(this@CoachActivity)
                                .load(R.drawable.leeds_coach)
                                .into(binding.coachPhoto)
                        }
                        
                        is CoachUiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.errorMessage.visibility = View.VISIBLE
                            binding.errorMessage.text = state.message
                            
                            Toast.makeText(
                                this@CoachActivity,
                                "Error: ${state.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }
}