package com.example.responsi1mobileh1d023077.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.responsi1mobileh1d023077.data.model.SquadMember
import com.example.responsi1mobileh1d023077.databinding.FragmentSquadMemberDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*

class SquadMemberDetailFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSquadMemberDetailBinding? = null
    private val binding get() = _binding!!

    private var squadMember: SquadMember? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            squadMember = it.getParcelable(ARG_SQUAD_MEMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSquadMemberDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        squadMember?.let { member ->
            binding.tvDetailName.text = member.name
            binding.tvDetailPosition.text = member.position
            binding.tvDetailNationality.text = member.nationality
            
            // Format date of birth
            val formattedDate = try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                val date = inputFormat.parse(member.dateOfBirth)
                outputFormat.format(date ?: Date())
            } catch (e: Exception) {
                member.dateOfBirth
            }
            binding.tvDetailDateOfBirth.text = formattedDate
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_SQUAD_MEMBER = "squad_member"

        fun newInstance(squadMember: SquadMember): SquadMemberDetailFragment {
            return SquadMemberDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_SQUAD_MEMBER, squadMember)
                }
            }
        }
    }
}