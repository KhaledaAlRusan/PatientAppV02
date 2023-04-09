package com.example.patientappv02.presentation.features.patients.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.patientappv02.domain.models.patients.Data
import com.example.patientappv02.presentation.databinding.RowPatientBinding
import androidx.recyclerview.widget.ListAdapter

class PatientAdapter(private val onDeletePatient:(id:String?)->Unit
    ):ListAdapter<Data,PatientAdapter.PatientViewHolder>(DiffCallBack) {
    var indexLastSelected = -1
    inner class PatientViewHolder(private val binding: RowPatientBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(model: Data?,position: Int){
            binding.model = model
            binding.cardView.setOnClickListener{
                if (position != indexLastSelected && indexLastSelected !=-1) {
                        getItem(indexLastSelected)?.selected = false
                        notifyItemChanged(indexLastSelected)
                }
                indexLastSelected = position
                getItem(position)?.selected = true
                notifyItemChanged(position)
            }
            binding.ivDelete.setOnClickListener {
                onDeletePatient(model?.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = RowPatientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PatientViewHolder(binding)
    }



    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val model = getItem(position)
        return holder.bind(model,position)
    }


    private object DiffCallBack:DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }
}