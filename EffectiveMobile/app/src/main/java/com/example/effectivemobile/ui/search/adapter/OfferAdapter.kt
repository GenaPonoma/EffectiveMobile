package com.example.effectivemobile.ui.search.adapter


import android.content.Intent
import android.net.Uri.parse
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.R
import com.example.effectivemobile.data.model.Offer
import com.example.effectivemobile.databinding.ItemOfferBinding

class OfferAdapter : ListAdapter<Offer, OfferViewHolder>(OfferDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding)

    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            textBlock.text = item.title
            textButton.visibility = if (item.button?.text == null) View.INVISIBLE else View.VISIBLE
            Log.d(" icon.setImageResource(R.drawable.search)", "id: ${item.id}")
            when (item.id) {

                "near_vacancies" -> {

                    icon.setImageResource(R.drawable.vacation)
                    icon.background = getDrawable(icon.context, R.drawable.circle)
                }

                "level_up_resume" -> {
                    icon.setImageResource(R.drawable.smal)
                    icon.background = getDrawable(icon.context, R.drawable.circle)
                }

                "temporary_job" -> {
                    icon.setImageResource(R.drawable.vacation)
                    icon.background = getDrawable(icon.context, R.drawable.circle)
                }

                else -> {
                    icon.background = getDrawable(icon.context, R.drawable.circle_blue)
                }


            }
            root.setOnClickListener {
                // Проверяем, что ссылка не пустая и корректная
                if (!item.link.isNullOrEmpty()) {
                    try {
                        // Создаем Intent для открытия ссылки в браузере
                        val intent = Intent(Intent.ACTION_VIEW, parse(item.link))
                        // Запускаем Activity с этим Intent'ом
                        it.context.startActivity(intent)
                    } catch (e: Exception) {
                        // Обрабатываем возможные ошибки, например, если ссылка некорректна
                        Log.e("Adapter", "Error opening link: ${e.message}")
                    }
                }
            }
        }
    }


}

private class OfferDiffCallback : DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }
}

class OfferViewHolder(val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root)