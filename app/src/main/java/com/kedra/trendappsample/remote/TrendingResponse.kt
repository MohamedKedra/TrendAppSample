package com.kedra.trendappsample.remote

import androidx.annotation.Keep

@Keep
class TrendingResponse : ArrayList<Trending>()

@Keep
data class Trending(
    val author: String,
    val avatar: String,
    val builtBy: List<BuiltBy>,
    val currentPeriodStars: Int,
    val description: String,
    val forks: Int,
    val language: String,
    val languageColor: String,
    val name: String,
    val stars: Int,
    val url: String
)

@Keep
data class BuiltBy(
    val avatar: String,
    val href: String,
    val username: String
)