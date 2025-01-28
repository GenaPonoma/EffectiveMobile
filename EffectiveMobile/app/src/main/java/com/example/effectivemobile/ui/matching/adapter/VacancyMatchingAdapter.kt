package com.example.effectivemobile.ui.matching.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.Constants.COMPANY
import com.example.effectivemobile.Constants.EXPERIENCE
import com.example.effectivemobile.Constants.PUBLISHED_DATE
import com.example.effectivemobile.Constants.TITLE
import com.example.effectivemobile.Constants.TOWN
import com.example.effectivemobile.Constants.VACANCY_ID
import com.example.effectivemobile.R
import com.example.effectivemobile.data.model.Vacancy
import com.example.effectivemobile.databinding.RecyclerVacanciesViewBinding

class VacancyMatchingAdapter :
    ListAdapter<Vacancy, VacancyMatchingAViewHolder>(VacancyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyMatchingAViewHolder {
        val binding =
            RecyclerVacanciesViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacancyMatchingAViewHolder(binding)
    }


    override fun onBindViewHolder(holder: VacancyMatchingAViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            binding.apply {
                iconFavorites.setImageResource(if (item.isFavorite == true) R.drawable.favorit_active else R.drawable.favorites)
                if (item.lookingNumber != null) {
                    viewsCountLooking.text = root.context.resources.getQuantityString(
                        R.plurals.count_looking,
                        item.lookingNumber,
                        item.lookingNumber
                    )
                } else {
                    viewsCountLooking.text = root.context.resources.getQuantityString(
                        R.plurals.count_looking,
                        0,
                        0
                    )
                }
                vacancyTitle.text = item.title
                location.text = item.address?.town
                companyName.text = item.company
                experienceRequired.text = item.experience?.previewText
                postedDate.text = item.publishedDate


                // Добавляем обработчик кликов на весь элемент карточки

                root.setOnClickListener {
                    val navController = Navigation.findNavController(it)
                    navController.navigate(
                        R.id.action_MatchingFragment_to_VacancyFragment

                    )
                }
            }
        }
    }




}

private class VacancyDiffCallback : DiffUtil.ItemCallback<Vacancy>() {
    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem == newItem
    }
}

class VacancyMatchingAViewHolder(val binding: RecyclerVacanciesViewBinding) :
    RecyclerView.ViewHolder(binding.root)