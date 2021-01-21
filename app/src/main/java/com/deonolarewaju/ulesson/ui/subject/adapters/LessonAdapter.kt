package com.deonolarewaju.ulesson.ui.subject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deonolarewaju.ulesson.R
import com.deonolarewaju.ulesson.repository.model.Lesson
import com.deonolarewaju.ulesson.ui.subject.SubjectViewModel
import com.deonolarewaju.ulesson.util.loadImage
import kotlinx.android.synthetic.main.lesson_item.view.*

class LessonAdapter(private val viewModel: SubjectViewModel) :
    ListAdapter<Lesson, LessonAdapter.ViewHolder>(
        LessonDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.lesson_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel, getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(viewModel: SubjectViewModel, lesson: Lesson) {
            with(itemView) {
                img.loadImage(lesson.icon)
                lessonTitle.text = lesson.name

                setOnClickListener {
                    viewModel.openVideo(lesson)
                }
            }
        }
    }
}


class LessonDiffCallback : DiffUtil.ItemCallback<Lesson>() {
    override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
        return oldItem == newItem
    }
}