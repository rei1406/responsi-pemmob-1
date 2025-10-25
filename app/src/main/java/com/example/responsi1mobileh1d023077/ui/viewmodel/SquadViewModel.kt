package com.example.responsi1mobileh1d023077.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023077.data.model.SquadMember
import com.example.responsi1mobileh1d023077.data.repository.FootballRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class SquadUiState {
    object Loading : SquadUiState()
    data class Success(val squadMembers: List<SquadMember>) : SquadUiState()
    data class Error(val message: String) : SquadUiState()
}

class SquadViewModel : ViewModel() {
    private val repository = FootballRepository()

    private val _uiState = MutableStateFlow<SquadUiState>(SquadUiState.Loading)
    val uiState: StateFlow<SquadUiState> = _uiState

    fun fetchSquadData() {
        viewModelScope.launch {
            _uiState.value = SquadUiState.Loading
            val result = repository.getClubInfo()
            result.fold(
                onSuccess = { clubResponse ->
                    _uiState.value = SquadUiState.Success(clubResponse.squad)
                },
                onFailure = { exception ->
                    _uiState.value = SquadUiState.Error("Failed to load squad data: ${exception.message}")
                }
            )
        }
    }
}