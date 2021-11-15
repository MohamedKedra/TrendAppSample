package com.kedra.trendappsample.remote

import io.reactivex.Single

class TrendingRepository(private val repoService: RepoService) {

    fun getTrendingList(): Single<TrendingResponse> {
       return repoService.getTrendingRepos()
    }


    private val mockList = listOf(
        Trending(
            author = "xingshaocheng",
            name = "architect-awesome",
            avatar = "https://github.com/xingshaocheng.png",
            url = "https://github.com/xingshaocheng/architect-awesome",
            description = "后端架构师技术图谱",
            language = "Go",
            languageColor = "#3572A5",
            stars = 7333,
            forks = 1546,
            currentPeriodStars = 1528,
            builtBy = emptyList()
        ),
        Trending(
            author = "xingshaocheng",
            name = "architect-awesome",
            avatar = "https://github.com/xingshaocheng.png",
            url = "https://github.com/xingshaocheng/architect-awesome",
            description = "后端架构师技术图谱",
            language = "Go",
            languageColor = "#3572A5",
            stars = 7333,
            forks = 1546,
            currentPeriodStars = 1528,
            builtBy = emptyList()
        ),
        Trending(
            author = "xingshaocheng",
            name = "architect-awesome",
            avatar = "https://github.com/xingshaocheng.png",
            url = "https://github.com/xingshaocheng/architect-awesome",
            description = "后端架构师技术图谱",
            language = "Go",
            languageColor = "#3572A5",
            stars = 7333,
            forks = 1546,
            currentPeriodStars = 1528,
            builtBy = emptyList()
        ),
        Trending(
            author = "xingshaocheng",
            name = "architect-awesome",
            avatar = "https://github.com/xingshaocheng.png",
            url = "https://github.com/xingshaocheng/architect-awesome",
            description = "后端架构师技术图谱",
            language = "Go",
            languageColor = "#3572A5",
            stars = 7333,
            forks = 1546,
            currentPeriodStars = 1528,
            builtBy = emptyList()
        )
    )
}