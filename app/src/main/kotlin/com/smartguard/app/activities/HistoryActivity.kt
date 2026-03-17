package com.smartguard.app.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartguard.app.database.Incident
import com.smartguard.app.databinding.ActivityHistoryBinding
import com.smartguard.app.databinding.ItemIncidentBinding
import com.smartguard.app.viewmodel.SecurityViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var viewModel: SecurityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SecurityViewModel::class.java]

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = IncidentAdapter()
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = adapter

        viewModel.incidents.observe(this) { incidents ->
            adapter.setIncidents(incidents)
        }
    }
}

class IncidentAdapter : RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder>() {
    private var incidents: List<Incident> = emptyList()

    fun setIncidents(newIncidents: List<Incident>) {
        this.incidents = newIncidents
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
        val binding = ItemIncidentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IncidentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        holder.bind(incidents[position])
    }

    override fun getItemCount(): Int = incidents.size

    class IncidentViewHolder(private val binding: ItemIncidentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(incident: Incident) {
            binding.tvEventType.text = incident.eventType
            binding.tvLocation.text = "Location: ${incident.location}"
            val dateFormat = SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.getDefault())
            binding.tvTimestamp.text = dateFormat.format(Date(incident.timestamp))
        }
    }
}
