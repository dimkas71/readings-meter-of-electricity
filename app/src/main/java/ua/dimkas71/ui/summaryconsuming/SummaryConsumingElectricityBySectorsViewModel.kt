package ua.dimkas71.ui.summaryconsuming

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ua.dimkas71.data.SummaryConsumingBySectors
import ua.dimkas71.data.SummaryConsumingBySectorsRepository

import ua.dimkas71.common.Result
import ua.dimkas71.main.R
import javax.inject.Inject

data class SummaryConsumingElectricityBySectorsUiState(
    val items: List<SummaryConsumingBySectors> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessageId: Int? = null
)

@HiltViewModel
class SummaryConsumingElectricityBySectorsViewModel @Inject constructor(
    private val summaryConsumingRepository: SummaryConsumingBySectorsRepository,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _errorMessageId: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val _items = summaryConsumingRepository.getSummaryConsuming().map {
        Result.Success(it)
    }.catch<Result<List<SummaryConsumingBySectors>>>{emit(Result.Error("Error at loading"))}

    val uiState: StateFlow<SummaryConsumingElectricityBySectorsUiState> =
        combine(_isLoading, _items) { isLoading, items ->
            when (items) {
                Result.Loading -> {
                    SummaryConsumingElectricityBySectorsUiState(isLoading = true)
                }

                is Result.Error -> {
                    SummaryConsumingElectricityBySectorsUiState(errorMessageId = R.string.loading_error_message)
                }
                is Result.Success -> {
                    SummaryConsumingElectricityBySectorsUiState(
                        items = items.data,
                        isLoading = isLoading,
                        errorMessageId = null
                    )
                }
            }

        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = SummaryConsumingElectricityBySectorsUiState(isLoading = true)
            )





}