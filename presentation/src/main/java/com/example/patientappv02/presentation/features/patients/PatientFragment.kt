package com.example.patientappv02.presentation.features.patients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patientappv02.presentation.databinding.FragmentPatientBinding
import com.example.patientappv02.presentation.features.adapter.PatientAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientFragment:Fragment() {

    private val viewModel:PatientViewModel by viewModels()
    private lateinit var binding: FragmentPatientBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPatientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()

    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.patientStateFlow.collect{
                binding.recyclerView.adapter = PatientAdapter(it)
            }

        }

        lifecycleScope.launch {
            viewModel.loadingStateFlow.collect{
                binding.progressCircular.isVisible = it
            }
        }

        lifecycleScope.launch {
            viewModel.errorStateFlow.collect{
                if (it != null){
                    Toast.makeText(requireContext(),it?.message.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}