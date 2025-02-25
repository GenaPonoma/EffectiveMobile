package com.example.effectivemobile.ui.favourites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.R
import com.example.effectivemobile.data.model.Vacancy
import com.example.effectivemobile.databinding.RecyclerVacanciesViewBinding

class VacancyFavoritesAdapter(private val onItemClicked: (String) -> Unit) :
    ListAdapter<Vacancy, VacancyFavoritesViewHolder>(VacancyMatchingDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyFavoritesViewHolder {
        val binding =
            RecyclerVacanciesViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacancyFavoritesViewHolder(binding)
    }


    override fun onBindViewHolder(holder: VacancyFavoritesViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            binding.apply {
                iconFavorites.setImageResource(if (item.isFavorite == true) R.drawable.favorit_active else R.drawable.favorites)

                if (item.lookingNumber != null) {
                    viewsCountLooking.text = root.context.resources.getQuantityString(
                        R.plurals.count_looking,
                        item.lookingNumber!!,
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
                        R.id.action_NavigationSearch_to_VacancyFragment
                    )
                }
                iconFavorites.setOnClickListener {
                    if (item.isFavorite == true) {
                        item.isFavorite = false
                        iconFavorites.setImageResource(R.drawable.favorites)
                        onItemClicked(item.id.toString())
                    } else {
                        onItemClicked(item.id.toString())
                        iconFavorites.setImageResource(R.drawable.favorit_active)
                        item.isFavorite = true
                    }
                }

            }

        }
    }


}

private class VacancyMatchingDiffCallback : DiffUtil.ItemCallback<Vacancy>() {
    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem == newItem
    }
}

class VacancyFavoritesViewHolder(val binding: RecyclerVacanciesViewBinding) :
    RecyclerView.ViewHolder(binding.root)