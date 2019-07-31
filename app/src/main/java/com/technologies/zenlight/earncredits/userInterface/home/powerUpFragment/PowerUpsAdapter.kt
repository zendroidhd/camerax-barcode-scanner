package com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.data.model.api.PowerUps
import com.technologies.zenlight.earncredits.databinding.PowerUpsListRowBinding

class PowerUpsAdapter(private val powerUps: ArrayList<PowerUps>, private val callbacks: PowerUpsCallbacks)
    : RecyclerView.Adapter<PowerUpsAdapter.PowerUpsViewHolder>() {


    inner class PowerUpsViewHolder(val binding: PowerUpsListRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(powerUp: PowerUps) {
            binding.tvTitle.text = powerUp.description
            binding.tvCreditsValue.text = powerUp.cost.toString()

            binding.ivComplete.setOnClickListener{
                callbacks.onCompletePowerUpClicked(powerUp)
            }

            binding.btnDelete.setOnClickListener {
                callbacks.onDeletePowerupClicked(powerUp)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PowerUpsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: PowerUpsListRowBinding = DataBindingUtil.inflate(inflater, R.layout.power_ups_list_row,parent,false)
        return PowerUpsViewHolder(binding)
    }

    override fun getItemCount() = powerUps.size

    override fun onBindViewHolder(holder: PowerUpsViewHolder, position: Int) {
        holder.bind(powerUps[position])
    }
}