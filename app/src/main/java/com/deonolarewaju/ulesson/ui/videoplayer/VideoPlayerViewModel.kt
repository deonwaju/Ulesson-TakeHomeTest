package com.deonolarewaju.ulesson.ui.videoplayer

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deonolarewaju.ulesson.repository.model.RecentlyViewed
import com.deonolarewaju.ulesson.repository.source.repository.Repository
import kotlinx.coroutines.launch

class VideoPlayerViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    fun addRecentView(recentlyViewed: RecentlyViewed) {
        viewModelScope.launch {
            repository.saveRecentView(recentlyViewed)
        }
    }
}