package org.codeslu.wpay.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.codeslu.wpay.data.model.Promo
import org.codeslu.wpay.data.model.User

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    init {
        viewModelScope.launch{
            val promos = listOf(
                Promo(
                    title = "Black Friday deal",
                    description = "Get discount for every topup, transfer and payment\"",
                    hasDiscount = true,
                    discountPercentage = 30
                ),
                Promo(
                    title = "Special Offer for Today’s Top Up",
                    description = "Get discount for every top up, transfer and payment",
                )
            )
            _uiState.update { it.copy(currentUser = User(firstName = "Andre"), availableBalance = 15901.0, promos = promos) }
        }
    }
}