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