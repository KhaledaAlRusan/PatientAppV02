package com.example.patientappv02.presentation.features.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.patientappv02.presentation.R
import com.example.patientappv02.presentation.databinding.FragmentPatientBinding
import com.example.patientappv02.presentation.features.patients.adapter.PatientAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientFragment:Fragment() {

    private val viewModel:PatientViewModel by viewModels()
    private lateinit var binding: FragmentPatientBinding
    private lateinit var adapter: PatientAdapter

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

        initAdapter()
        initObserver()
        initListener()
    }

    private fun initAdapter() {
        adapter  = PatientAdapter(::deletePatient)
        binding.recyclerView.adapter = adapter
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.patientStateFlow.collect{
                adapter.submitList(it)
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
        lifecycleScope.launch {
            viewModel.deletePatientLiveData.observe(viewLifecycleOwner){
                Toast.makeText(requireContext(),"Patient has been deleted",Toast.LENGTH_SHORT).show()
                viewModel.getPatients()
            }
        }
    }


    private fun initListener() {
        binding.fabAdd.setOnClickListener {
            Log.d("TAGGG", "didn't Enter the fragment")
            findNavController().navigate(R.id.addPatientFragment)
        }

        binding.swipeRefresher.setOnRefreshListener {
            viewModel.getPatients()
            lifecycleScope.launch {
                delay(3000)
                binding.swipeRefresher.isRefreshing = false
            }
        }
    }


    fun deletePatient(id:String?){
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("Do you want to delete this item?")
            .setNegativeButton("No"){dialog,_ ->
                dialog.dismiss()
            }
            .setPositiveButton("Yes"){dialog,_ ->
                if (id != null) {
                    viewModel.deletePatients(id)
                }
                dialog.dismiss()
            }
            .show()
    }

}