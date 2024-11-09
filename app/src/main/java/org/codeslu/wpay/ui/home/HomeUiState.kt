package org.codeslu.wpay.ui.home

import org.codeslu.wpay.data.model.Promo
import org.codeslu.wpay.data.model.User

data class HomeUiState(
    val isLoading: Boolean = true,
    val currentUser: User = User(),
    val availableBalance: Double = 0.0,
    val promos: List<Promo> = emptyList()
)
