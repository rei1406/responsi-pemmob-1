package com.example.responsi1mobileh1d023077.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.responsi1mobileh1d023077.databinding.ActivitySquadBinding
import com.example.responsi1mobileh1d023077.ui.adapter.SquadAdapter
import com.example.responsi1mobileh1d023077.ui.fragment.SquadMemberDetailFragment
import com.example.responsi1mobileh1d023077.ui.viewmodel.SquadUiState
import com.example.responsi1mobileh1d023077.ui.viewmodel.SquadViewModel
import kotlinx.coroutines.launch

class SquadActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySquadBinding
    private val viewModel: SquadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        viewModel.fetchSquadData()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewSquad.layoutManager = LinearLayoutManager(this)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is SquadUiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerViewSquad.visibility = View.GONE
                            binding.tvErrorMessage.visibility = View.GONE
                        }
                        is SquadUiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerViewSquad.visibility = View.VISIBLE
                            binding.tvErrorMessage.visibility = View.GONE
                            
                            val adapter = SquadAdapter(state.squadMembers) { squadMember ->
                                val detailFragment = SquadMemberDetailFragment.newInstance(squadMember)
                                detailFragment.show(supportFragmentManager, "SquadMemberDetail")
                            }
                            binding.recyclerViewSquad.adapter = adapter
                        }
                        is SquadUiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerViewSquad.visibility = View.GONE
                            binding.tvErrorMessage.visibility = View.VISIBLE
                            binding.tvErrorMessage.text = state.message
                        }
                    }
                }
            }
        }
    }
}