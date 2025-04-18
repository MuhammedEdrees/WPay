package org.codeslu.wpay.app.di

import org.codeslu.wpay.ui.home.HomeViewModel
import org.codeslu.wpay.ui.notifications.NotificationsViewModel
import org.codeslu.wpay.ui.paymentreceipt.PaymentReceiptViewModel
import org.codeslu.wpay.ui.statistics.StatisticsViewModel
import org.codeslu.wpay.ui.summarytransaction.SummaryTransactionViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::NotificationsViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::StatisticsViewModel)
    viewModelOf(::SummaryTransactionViewModel)
    viewModelOf(::PaymentReceiptViewModel)
}