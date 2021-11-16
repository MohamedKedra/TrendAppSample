package com.kedra.trendappsample

import com.kedra.trendappsample.remote.RepoService
import com.kedra.trendappsample.remote.TrendingResponse
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.inject.Inject

@RunWith(JUnit4::class)
class TrendingRepositoryTest {

    @Inject
    lateinit var repoService: RepoService

    @Test
    fun `calling api get data very well`() {
        repoService.getTrendingRepos().doOnSuccess { assert(!it.isNullOrEmpty()) }
        repoService.getTrendingRepos().doAfterSuccess { assert(mockList == it) }
    }

    @Test
    fun `calling api return error`() {
        repoService.getTrendingRepos().doOnError { assert(it == Throwable("Error found"))}
    }

    private val mockList = listOf(
        TrendingResponse(
            author = "xingshaocheng",
            name = "architect-awesome",
            avatar = "https://github.com/xingshaocheng.png",
            url = "https://github.com/xingshaocheng/architect-awesome",
            description = "后端架构师技术图谱",
            language = "Go",
            languageColor = "#2f12e6",
            stars = 7333,
            forks = 1546,
            currentPeriodStars = 1528,
            builtBy = emptyList()
        ),
        TrendingResponse(
            author = "Mohamed",
            name = "flutter-awesome",
            avatar = "https://github.com/davideuler.png",
            url = "https://github.com/xingshaocheng/architect-awesome",
            description = "Container Runtime Sandbox",
            language = "Java",
            languageColor = "#05450c",
            stars = 1200,
            forks = 300,
            currentPeriodStars = 1528,
            builtBy = emptyList()
        ),
        TrendingResponse(
            author = "Ryad",
            name = "vbgfr-awesome",
            avatar = "https://github.com/google.png",
            url = "https://github.com/xingshaocheng/architect-awesome",
            description = "A React environment for cross platform native desktop apps",
            language = "C++",
            languageColor = "#b04c1e",
            stars = 7333,
            forks = 1546,
            currentPeriodStars = 1528,
            builtBy = emptyList()
        ),
        TrendingResponse(
            author = "Ramy",
            name = "MVVM",
            avatar = "https://github.com/kusti8.png",
            url = "https://github.com/xingshaocheng/architect-awesome",
            description = "后端架构师技术图谱",
            language = "Ruby",
            languageColor = "#1172A5",
            stars = 7333,
            forks = 1546,
            currentPeriodStars = 1528,
            builtBy = emptyList()
        )
    )
}