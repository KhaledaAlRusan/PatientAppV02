package com.example.patientappv02.presentation.features.patients.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patientappv02.domain.models.patients.Data
import com.example.patientappv02.presentation.databinding.RowPatientBinding

class PatientAdapter(private val patients:List<Data?>?):RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {
    var indexLastSelected = -1
    inner class PatientViewHolder(private val binding: RowPatientBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(model: Data?,position: Int){
            binding.model = model
            binding.cardView.setOnClickListener{
                if (position != indexLastSelected) {
                    if (indexLastSelected >= 0 && indexLastSelected < (patients?.size ?: 0)) {
                        patients?.get(indexLastSelected)?.selected = false
                        notifyItemChanged(indexLastSelected)
                    }
                }
                indexLastSelected = position
                patients?.get(position)?.selected = true
                notifyItemChanged(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = RowPatientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PatientViewHolder(binding)
    }

    override fun getItemCount() = patients?.size ?:0


    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val model = patients?.get(position)
        return holder.bind(model,position)
    }
}