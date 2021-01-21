package com.deonolarewaju.ulesson.util

import com.deonolarewaju.ulesson.repository.model.*

object TestObjectUtil {
    val lessons = listOf(
        Lesson(45L, "lesson1", "icon1.png", "lesson1.mp4", 3L, 14L),
        Lesson(27L, "lesson2", "icon2.png", "lesson2.mp4", 3L, 14L),
        Lesson(44L, "lesson3", "icon3.png", "lesson3.mp4", 3L, 14L)
    )

    val chapters = listOf(
        Chapter(14L, "Gravity", lessons)
    )

    val subjects = listOf(
        Subject(3L, "Physics", "physics.png", chapters),
        Subject(4L, "Chemistry", "chemistry.png", chapters),
        Subject(5L, "Biology", "biology.png", chapters),
        Subject(6L, "English", "english.png", chapters)
    )

    val recentViews = listOf(
        RecentlyViewed(3L, "Physics", "Gravity"),
        RecentlyViewed(4L, "Chemistry", "Gravity"),
        RecentlyViewed(5L, "Biology", "Gravity"),
        RecentlyViewed(6L, "English", "Adjective")
    )

    val subjectDataResponse = SubjectDataResponse(
        SubjectData(status = true, message = true, subjects = subjects)
    )
}