package com.kedra.trendappsample.remote

import io.reactivex.Single
import javax.inject.Inject

class TrendingRepository @Inject constructor(private val repoService: RepoService) {

    fun  getList() : Single<List<TrendingResponse>> = repoService.getTrendingRepos()


}