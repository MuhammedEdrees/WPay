package org.codeslu.wpay.ui.paymentreceipt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.codeslu.wpay.ui.util.DummyValues

class PaymentReceiptViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(PaymentReceiptUiState())
    val uiState = _uiState.asStateFlow()
    init {
        viewModelScope.launch{
            _uiState.update { it.copy(expense = DummyValues.recentExpenses.first()) }
        }
    }
}