package ua.dimkas71.ui.summaryconsuming

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ua.dimkas71.data.SummaryConsumingBySectors
import ua.dimkas71.data.repository.SummaryConsumingBySectorsRepository
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@Immutable
data class SummaryConsumingElectricityBySectorsUiState(
    val items: List<SummaryConsumingBySectors> = emptyList(),
    val period: String = "01.01.1970",
    val isLoading: Boolean = false,
    val errorMessageId: Int? = null
)

@HiltViewModel
class SummaryConsumingElectricityBySectorsViewModel @Inject constructor(
    private val repository: SummaryConsumingBySectorsRepository
): ViewModel() {

    var uiState by mutableStateOf(SummaryConsumingElectricityBySectorsUiState())
        private set

    private var fetchJob: Job? = null

    fun getSummaryByPeriod(period: String) {

        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            val newItems = repository.getSummaryByPeriod(period)
            uiState = uiState.copy(items = newItems)
        }
    }
}