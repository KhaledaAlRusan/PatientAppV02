package com.example.patientappv02.presentation.features.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patientappv02.domain.models.patients.Data
import com.example.patientappv02.presentation.databinding.RowPatientBinding

class PatientAdapter(private val patients:List<Data?>?):RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    inner class PatientViewHolder(private val binding: RowPatientBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(model: Data?){
            binding.model = model
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = RowPatientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PatientViewHolder(binding)
    }

    override fun getItemCount() = patients?.size ?:0


    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val model = patients?.get(position)
        return holder.bind(model)
    }
}