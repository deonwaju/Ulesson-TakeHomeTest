package com.deonolarewaju.ulesson.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.deonolarewaju.ulesson.repository.DummyRepository
import com.deonolarewaju.ulesson.util.Resources
import com.deonolarewaju.ulesson.util.CoroutineRule
import com.deonolarewaju.ulesson.util.TestObjectUtil
import com.deonolarewaju.ulesson.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutine = CoroutineRule()

    private lateinit var viewModel: HomeViewModel

    private val repository = DummyRepository()

    @Before
    fun init() {
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun `assert that call to network passes`() {
        mainCoroutine.runBlockingTest {
            viewModel.getSubjects()

            val status = viewModel.fetchingSubject.getOrAwaitValue()
            assertThat(status, `is`(Resources.success(Unit)))
        }
    }

    @Test
    fun `assert that error response is received when error occurs calling network`() {
        mainCoroutine.runBlockingTest {
            repository.setShouldReturnError(true)
            viewModel.getSubjects()

            val status = viewModel.fetchingSubject.getOrAwaitValue()
            assertThat(status, `is`(Resources.error("error occurred")))
        }
    }

    @Test
    fun `assert that call to network saves subject data`() {
        mainCoroutine.runBlockingTest {
            viewModel.getSubjects()

            val status = viewModel.fetchingSubject.getOrAwaitValue()
            assertThat(status, `is`(Resources.success(Unit)))

            val subjects = viewModel.subjects.getOrAwaitValue()
            assertThat(subjects, `is`(TestObjectUtil.subjects))
        }
    }

    @Test
    fun `assert that clicking view all button changes the button text to view less`() {
        //initial state of button
        viewModel.toggleButton("VIEW ALL")

        val textBtn = viewModel.toggleText.getOrAwaitValue()
        assertThat(textBtn, `is`("VIEW LESS"))
    }

    @Test
    fun `assert that clicking view less button changes the button text to view all`() {
        //initial state of button
        viewModel.toggleButton("VIEW LESS")

        val textBtn = viewModel.toggleText.getOrAwaitValue()
        assertThat(textBtn, `is`("VIEW ALL"))
    }

    @Test
    fun `assert that clicking view less button receives two(2) list of recent watched topics`() {
        repository.saveAllRecentView(TestObjectUtil.recentViews)

        viewModel.toggleButton("VIEW LESS")

        val recentViews = viewModel.recentViews.getOrAwaitValue()
        assertThat(recentViews.size, `is`(2))
        assertThat(recentViews, `is`(TestObjectUtil.recentViews.take(2)))
    }

    @Test
    fun `assert that clicking view all button receives all the list of recent watched topics`() {
        repository.saveAllRecentView(TestObjectUtil.recentViews)

        viewModel.toggleButton("VIEW ALL")

        val recentViews = viewModel.recentViews.getOrAwaitValue()
        assertThat(recentViews.size, `is`(TestObjectUtil.recentViews.size))
        assertThat(recentViews, `is`(TestObjectUtil.recentViews))
    }

    @Test
    fun `assert that open subject receives sets livedata`() {
        val subjectIdToOpen = TestObjectUtil.subjects[0].id
        viewModel.openSubject(subjectIdToOpen)

        val subjectId = viewModel.openSubjectId.getOrAwaitValue().getContentIfNotHandled()
        assertThat(subjectId, `is`(notNullValue()))
        assertThat(subjectId, `is`(subjectIdToOpen))
    }
}