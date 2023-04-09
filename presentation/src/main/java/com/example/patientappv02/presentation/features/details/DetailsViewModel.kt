package com.example.patientappv02.presentation.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientappv02.domain.models.patients.Data
import com.example.patientappv02.domain.usecase.details.DetailsPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsPatientUseCase: DetailsPatientUseCase,
    private val  state: SavedStateHandle):ViewModel()
{

    private val _detailsStateFlow: MutableStateFlow<Data?> = MutableStateFlow(null)
    val detailsStateFlow: StateFlow<Data?> = _detailsStateFlow


    private val _detailsLoadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val detailsLoadingStateFlow: StateFlow<Boolean> = _detailsLoadingStateFlow

    private val _detailsErrorStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val detailsErrorStateFlow: StateFlow<Exception?> = _detailsErrorStateFlow

    init {
        details()
    }


    fun details(){
        val id  = state.get<String>("id")?:"-1"

        viewModelScope.launch {

            _detailsLoadingStateFlow.emit(true)
            try {
                _detailsStateFlow.emit(detailsPatientUseCase(id))
            }
            catch (e:Exception){
                _detailsErrorStateFlow.emit(e)
            }
            _detailsLoadingStateFlow.emit(false)

        }
    }
}