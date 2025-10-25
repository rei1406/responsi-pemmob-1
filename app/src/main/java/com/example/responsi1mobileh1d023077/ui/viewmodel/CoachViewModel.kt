package com.example.responsi1mobileh1d023077.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023077.data.model.Coach
import com.example.responsi1mobileh1d023077.data.repository.FootballRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class CoachViewModel : ViewModel() {
    
    private val repository = FootballRepository()
    
    private val _uiState = MutableStateFlow<CoachUiState>(CoachUiState.Loading)
    val uiState: StateFlow<CoachUiState> = _uiState
    
    init {
        fetchCoachData()
    }
    
    fun fetchCoachData() {
        viewModelScope.launch {
            _uiState.value = CoachUiState.Loading
            
            repository.getClubInfo().fold(
                onSuccess = { clubResponse ->
                    val coach = clubResponse.coach
                    
                    // Format date of birth
                    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                    val date = inputFormat.parse(coach.dateOfBirth)
                    val formattedDate = date?.let { outputFormat.format(it) } ?: coach.dateOfBirth
                    
                    _uiState.value = CoachUiState.Success(
                        coach = coach,
                        formattedDateOfBirth = formattedDate
                    )
                },
                onFailure = { error ->
                    _uiState.value = CoachUiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }
}

sealed class CoachUiState {
    object Loading : CoachUiState()
    data class Success(val coach: Coach, val formattedDateOfBirth: String) : CoachUiState()
    data class Error(val message: String) : CoachUiState()
}