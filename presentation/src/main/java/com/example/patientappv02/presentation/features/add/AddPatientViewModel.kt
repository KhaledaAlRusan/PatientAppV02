package com.example.patientappv02.presentation.features.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientappv02.domain.models.add.AddPatientRequest
import com.example.patientappv02.domain.models.patients.Data
import com.example.patientappv02.domain.repo.PatientRepo
import com.example.patientappv02.domain.usecase.add.AddPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(private val addPatientUseCase: AddPatientUseCase):ViewModel() {


    private val _addPatientMutableStateFlow: MutableLiveData<Data?> = MutableLiveData()
    val addPatientStateFlow:LiveData<Data?> = _addPatientMutableStateFlow


    private val _addLoadingMutableStateFlow: MutableLiveData<Boolean> = MutableLiveData()
    val addLoadingStateFlow:LiveData<Boolean> = _addLoadingMutableStateFlow

    private val _addErrorMutableStateFlow: MutableLiveData<Exception?> = MutableLiveData()
    val addErrorStateFlow:LiveData<Exception?> = _addErrorMutableStateFlow


    fun addPatient(addPatientRequest: AddPatientRequest) {
        viewModelScope.launch {
            _addLoadingMutableStateFlow.postValue(true)

            try {
                _addPatientMutableStateFlow.postValue(addPatientUseCase(addPatientRequest))
            }
            catch (e:Exception){
                _addErrorMutableStateFlow.postValue(e)
            }
            _addLoadingMutableStateFlow.postValue(false)

        }
    }
}