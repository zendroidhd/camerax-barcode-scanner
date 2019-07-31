package com.technologies.zenlight.earncredits.userInterface.home.challenges

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.data.model.api.Challenges
import com.technologies.zenlight.earncredits.databinding.ChallengesListRowBinding

class ChallengesAdapter(private val challenges: ArrayList<Challenges>, private val callbacks: ChallengesCallbacks)
    : RecyclerView.Adapter<ChallengesAdapter.ChallengesViewHolder>() {

   inner class ChallengesViewHolder(val binding: ChallengesListRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(challenge: Challenges) {
            toggleButton(challenge)
            binding.tvTitle.text = challenge.description
            binding.tvCompletedValue.text = challenge.getCompleteByDate()
            binding.tvCreatedValue.text = challenge.getCreatedOnDate()
            binding.tvCreditsValue.text = challenge.credit.toString()

            binding.ivComplete.setOnClickListener{
                callbacks.onCompleteChallengeClicked(challenge)
            }

            binding.btnDelete.setOnClickListener{
                callbacks.onDeleteChallengeClicked(challenge)
            }
        }

       private fun toggleButton(challenge: Challenges) {
           val days = challenge.getDaysLeftToComplete()
           when {
               days >= 2 -> binding.ivComplete.setImageResource(R.drawable.green_toggle_button)
               days == 1 -> binding.ivComplete.setImageResource(R.drawable.yellow_toggle_button)
               days == 0 -> binding.ivComplete.setImageResource(R.drawable.red_toggle_button)
               else -> binding.ivComplete.setImageResource(R.drawable.purple_toggle_button)
           }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ChallengesListRowBinding = DataBindingUtil.inflate(inflater, R.layout.challenges_list_row,parent,false)
        return ChallengesViewHolder(
            binding
        )
    }

    override fun getItemCount() = challenges.size

    override fun onBindViewHolder(holder: ChallengesViewHolder, position: Int) {
        holder.bind(challenges[position])
    }


}