package com.technologies.zenlight.earncredits.userInterface.home.homeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.databinding.FragmentContainerBinding
import com.technologies.zenlight.earncredits.databinding.ItemListRowBinding

class ChallengesAdapter: RecyclerView.Adapter<ChallengesAdapter.ChallengesViewHolder>() {

    class ChallengesViewHolder(val binding: ItemListRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemListRowBinding = DataBindingUtil.inflate(inflater, R.layout.item_list_row,parent,false)
        return ChallengesViewHolder(binding)
    }

    override fun getItemCount() = 5

    override fun onBindViewHolder(holder: ChallengesViewHolder, position: Int) {
        holder.bind()
    }


}