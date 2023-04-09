package com.example.patientappv02.presentation.features.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientappv02.domain.models.delete.DeletePatientResponse
import com.example.patientappv02.domain.models.patients.Data
import com.example.patientappv02.domain.repo.PatientRepo
import com.example.patientappv02.domain.usecase.delete.DeletePatientUseCase
import com.example.patientappv02.domain.usecase.patients.GetPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(private val getPatientUseCase: GetPatientUseCase,
private val deletePatientUseCase: DeletePatientUseCase
): ViewModel() {

    //State flow functionality for getting all the data
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
                _patientMutableStateFlow.emit(getPatientUseCase())
            }
            catch (e:Exception)
            {
                _errorMutableStateFlow.emit(e)
            }
            _loadingMutableStateFlow.emit(false)

        }
    }


    //Live Data implementation for delete patient

    private val _deletePatientLiveData:MutableLiveData<DeletePatientResponse?> = MutableLiveData()
    val deletePatientLiveData:LiveData<DeletePatientResponse?> = _deletePatientLiveData

    private val _deleteLoadingLiveData:MutableLiveData<Boolean> = MutableLiveData()
    val deleteLoadingLiveData:LiveData<Boolean> = _deleteLoadingLiveData

    private val _deleteErrorLiveData:MutableLiveData<Exception> = MutableLiveData()
    val deleteErrorLiveData:LiveData<java.lang.Exception> = _deleteErrorLiveData

    fun deletePatients(id:String){
        viewModelScope.launch {
            _deleteLoadingLiveData.postValue(true)
            try {
                _deletePatientLiveData.postValue(deletePatientUseCase(id))
            }
            catch (e:Exception)
            {
                _deleteErrorLiveData.postValue(e)
            }
            _deleteLoadingLiveData.postValue(false)

        }
    }

}