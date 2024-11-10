package org.codeslu.wpay.ui.summarytransaction

import org.codeslu.wpay.data.model.Card
import org.codeslu.wpay.data.model.Transaction

data class SummaryTransactionUiState(
    val transaction: Transaction = Transaction(),
    val cards: List<Card> = emptyList()
)
