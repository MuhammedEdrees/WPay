package org.codeslu.wpay.ui.summarytransaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.codeslu.wpay.ui.util.DummyValues

class SummaryTransactionViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SummaryTransactionUiState())
    val uiState = _uiState.asStateFlow()
    init{
        viewModelScope.launch{
            _uiState.update {
                it.copy(
                    transaction = DummyValues.transaction,
                    cards = listOf(DummyValues.card)
                )
            }
        }
    }
}