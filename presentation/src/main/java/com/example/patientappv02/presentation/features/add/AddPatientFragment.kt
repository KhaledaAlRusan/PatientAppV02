package com.example.patientappv02.presentation.features.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patientappv02.domain.models.add.AddPatientRequest
import com.example.patientappv02.presentation.databinding.FragmentAddPatientBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddPatientFragment:Fragment() {
    private val viewModel:AddPatientViewModel by viewModels()
    lateinit var binding:FragmentAddPatientBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPatientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initObserver()
    }

    private fun initObserver() {

        lifecycleScope.launch{
            viewModel.addPatientStateFlow.observe(viewLifecycleOwner){
                if(it != null){
                    Toast.makeText(requireContext(),"Patient added successfully", Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launch{
            viewModel.addErrorStateFlow.observe(viewLifecycleOwner){
                if(it != null){
                    Toast.makeText(requireContext(),"Error: ${it.message.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        lifecycleScope.launch{
            viewModel.addLoadingStateFlow.observe(viewLifecycleOwner){
                binding.progressCircular.isVisible = it
            }
        }
    }


    private fun infoIsValid(): Boolean {
        var isValid = true

        if(binding.editTextFullName.text?.isEmpty() == true){
            isValid = false
            binding.textFullName.error = "Field is Empty"
        }
        else{
            binding.textFullName.error = ""
        }
        if(binding.editTextEmail.text?.isEmpty()== true){
            isValid = false
            binding.textEmail.error = "Field is Empty"
        }
        else{
            binding.textEmail.error = ""
        }
        if(binding.editTextAddress.text?.isEmpty()== true){
            isValid = false
            binding.textAddress.error = "Field is Empty"
        }
        else{
            binding.textAddress.error = ""
        }
        if(binding.editTextBirthdate.text?.isEmpty()== true){
            isValid = false
            binding.textBirthdate.error = "Field is Empty"
        }
        else{
            binding.textBirthdate.error = ""
        }
        if(binding.editTextGender.text?.isEmpty()== true){
            isValid = false
            binding.textGender.error = "Field is Empty"
        }
        else{
            binding.textGender.error = ""
        }
        if(binding.editTextMobile.text?.isEmpty()== true){
            isValid = false
            binding.textMobile.error = "Field is Empty"
        }
        else{
            binding.textMobile.error = ""
        }
        return isValid
    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener{
            if(infoIsValid()){
                val body = getInfoPatient()
                viewModel.addPatient(body)
            }
        }
    }
    private fun getInfoPatient(): AddPatientRequest {
        return AddPatientRequest(
            name = binding.editTextFullName.text.toString(),
            address = binding.editTextAddress.text.toString(),
            gender = binding.editTextGender.text.toString(),
            birthdate = binding.editTextBirthdate.text.toString(),
            mobile = binding.editTextMobile.text.toString(),
            email = binding.editTextEmail.text.toString(),
        )
    }

}