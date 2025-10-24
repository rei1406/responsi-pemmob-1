package com.example.responsi1mobileh1d023077

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.responsi1mobileh1d023077.data.network.RetrofitClient
import com.example.responsi1mobileh1d023077.databinding.ActivityCoachBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class CoachActivity : ComponentActivity() {
    private lateinit var binding: ActivityCoachBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        fetchCoachData()
    }
    
    private fun fetchCoachData() {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.footballApi.getClubInfo()
                
                if (response.isSuccessful && response.body() != null) {
                    val clubResponse = response.body()!!
                    val coach = clubResponse.coach
                    
                    // Format date of birth
                    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                    val date = inputFormat.parse(coach.dateOfBirth)
                    val formattedDate = date?.let { outputFormat.format(it) } ?: coach.dateOfBirth
                    
                    // Update UI with coach data
                    binding.coachName.text = coach.name
                    binding.coachDateOfBirth.text = "Date of Birth: $formattedDate"
                    binding.coachNationality.text = "Nationality: ${coach.nationality}"
                    
                    // Load coach image (using default image from drawable)
                    // Note: The API doesn't provide coach images, so we're using the drawable resource
                    Glide.with(this@CoachActivity)
                        .load(R.drawable.leeds_coach)
                        .into(binding.coachPhoto)
                    
                } else {
                    Toast.makeText(
                        this@CoachActivity,
                        "Error: ${response.errorBody()?.string()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@CoachActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}