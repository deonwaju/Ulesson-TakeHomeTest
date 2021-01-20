package com.deonolarewaju.ulesson.ui.subject

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.deonolarewaju.ulesson.util.helper.Event
import com.deonolarewaju.ulesson.util.helper.Resources
import com.deonolarewaju.ulesson.data.model.Lesson
import com.deonolarewaju.ulesson.data.model.RecentlyViewed
import com.deonolarewaju.ulesson.data.model.Subject
import com.deonolarewaju.ulesson.data.source.repository.Repository

class SubjectViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private var subject: Subject? = null

    private val _navigateToVideo = MutableLiveData<Event<RecentlyViewed>>()
    val navigateToVideo: LiveData<Event<RecentlyViewed>> = _navigateToVideo

    fun getSubject(id: Long): LiveData<Resources<Subject>> =
        liveData {
            emit(repository.getSubject(id))
        }

    fun setSubject(subject: Subject) {
        this.subject = subject
    }

    fun openVideo(lesson: Lesson) {
        subject?.let { subject ->
            val topicName = subject.chapters.find { it.id == lesson.chapterId }!!.name
            _navigateToVideo.value = Event(
                RecentlyViewed(subject.id, subject.name, topicName, lesson.mediaUrl)
            )
        }
    }
}
