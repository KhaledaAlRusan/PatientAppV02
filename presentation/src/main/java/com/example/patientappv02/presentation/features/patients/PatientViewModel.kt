package com.example.patientappv02.presentation.features.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientappv02.domain.models.patients.Data
import com.example.patientappv02.domain.repo.PatientRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(private val repo: PatientRepo): ViewModel() {

    private val _patientMutableStateFlow:MutableStateFlow<List<Data?>?> = MutableStateFlow(emptyList())
     val patientStateFlow = _patientMutableStateFlow.asStateFlow()


    private val _loadingMutableStateFlow:MutableStateFlow<Boolean> = MutableStateFlow(true)
     val loadingStateFlow = _loadingMutableStateFlow.asStateFlow()

    private val _errorMutableStateFlow:MutableStateFlow<Exception?> = MutableStateFlow(null)
     val errorStateFlow = _errorMutableStateFlow.asStateFlow()

    init {
        getPatients()
    }

    fun getPatients(){
        viewModelScope.launch {
            _loadingMutableStateFlow.emit(true)
            try {
                _patientMutableStateFlow.emit(repo.getPatients())
            }
            catch (e:Exception)
            {
                _errorMutableStateFlow.emit(e)
            }
            _loadingMutableStateFlow.emit(false)

        }
    }


}