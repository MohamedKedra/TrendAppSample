package com.kedra.trendappsample.remote

import io.reactivex.Single
import retrofit2.http.GET

interface RepoService {

    @GET("")
    fun getTrendingRepos() : Single<TrendingResponse>
}