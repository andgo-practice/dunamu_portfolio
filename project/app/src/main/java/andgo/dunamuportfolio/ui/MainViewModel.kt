package andgo.dunamuportfolio.ui

import andgo.dunamuportfolio.domain.UpbitRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val upbitRepository: UpbitRepository
) : ViewModel() {

    fun subscribe() {
        viewModelScope.launch {

            launch {
                delay(5000)
                upbitRepository.subscribe()
            }

            launch {
                upbitRepository.response.collect {
                    Log.d("test", "$it")
                }

                upbitRepository.event.distinctUntilChanged().collect {
                    Log.d("test", it)
                }
            }

        }


    }
}