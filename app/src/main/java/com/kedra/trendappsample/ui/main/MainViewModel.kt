package com.kedra.trendappsample.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kedra.trendappsample.app.BaseViewModel
import com.kedra.trendappsample.app.LiveDataState
import com.kedra.trendappsample.remote.TrendingRepository
import com.kedra.trendappsample.remote.TrendingResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TrendingRepository) :
    BaseViewModel() {
    private val disposable = CompositeDisposable()
    private var liveDataState = LiveDataState<List<TrendingResponse>>()

    fun refreshHomeList(): LiveDataState<List<TrendingResponse>> {

        publishLoading(liveDataState)

        disposable.add(
            repository.getList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                    object : DisposableSingleObserver<List<TrendingResponse>>() {
                        override fun onSuccess(response: List<TrendingResponse>) {
                            publishResult(liveDataState, response)
                        }

                        override fun onError(error: Throwable) {
                            publishError(liveDataState, error)
                        }
                    }
                )
        )

//        publishResult(liveDataState, mockList)
        return liveDataState
    }

    private val mockList = listOf(
        TrendingResponse(
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
        TrendingResponse(
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
        TrendingResponse(
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
        TrendingResponse(
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