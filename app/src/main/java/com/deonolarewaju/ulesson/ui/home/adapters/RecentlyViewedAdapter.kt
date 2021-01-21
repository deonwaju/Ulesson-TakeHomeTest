package com.deonolarewaju.ulesson.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deonolarewaju.ulesson.R
import com.deonolarewaju.ulesson.repository.model.RecentlyViewed
import kotlinx.android.synthetic.main.recent_view_item.view.*

class RecentViewAdapter :
    ListAdapter<RecentlyViewed, RecentViewAdapter.ViewHolder>(RecentViewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.recent_view_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recentlyViewed: RecentlyViewed) {

            itemView.subjectTitle.text = recentlyViewed.subjectName
            itemView.topicName.text = recentlyViewed.topicName

            when (recentlyViewed.subjectName) {
                "Biology" -> setContent(
                    itemView,
                    R.color.colorGreen,
                    R.drawable.biology,
                    R.drawable.ic_biology_play
                )
                "Mathematics" -> setContent(
                    itemView,
                    R.color.colorRed,
                    R.drawable.mathematics,
                    R.drawable.ic_play
                )
                "Physics" -> setContent(
                    itemView,
                    R.color.colorPurple,
                    R.drawable.physics,
                    R.drawable.ic_physics_play
                )
                "English" -> setContent(
                    itemView,
                    R.color.colorPurpleDark,
                    R.drawable.biology,
                    R.drawable.ic_english_play
                )
                else -> setContent(
                    itemView,
                    R.color.colorOrange,
                    R.drawable.chemistry,
                    R.drawable.ic_chemistry_play
                )
            }
        }

        private fun setContent(
            itemView: View,
            @ColorRes
            color: Int,
            @DrawableRes
            imageBgDrawable: Int,
            @DrawableRes
            playIconDrawable: Int
        ) {
            itemView.imgContainer.backgroundTintList =
                ContextCompat.getColorStateList(itemView.context, color)

            itemView.imagebg.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    imageBgDrawable
                )
            )
            itemView.playIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    playIconDrawable
                )
            )

            itemView.subjectTitle.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    color
                )
            )
        }
    }
}

class RecentViewDiffCallback : DiffUtil.ItemCallback<RecentlyViewed>() {
    override fun areItemsTheSame(oldItem: RecentlyViewed, newItem: RecentlyViewed): Boolean {
        return oldItem.subjectId == newItem.subjectId
    }

    override fun areContentsTheSame(oldItem: RecentlyViewed, newItem: RecentlyViewed): Boolean {
        return oldItem == newItem
    }
}