package org.codeslu.wpay.ui.home.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.codeslu.wpay.R

enum class PaymentOptions(@DrawableRes val iconDrawable: Int, @StringRes val titleRes: Int) {
    ELECTRICITY(R.drawable.ic_electricity, R.string.electricity),
    INTERNET(R.drawable.ic_internet, R.string.internet),
    VOUCHER(R.drawable.ic_voucher, R.string.voucher),
    ASSURANCE(R.drawable.ic_assurance, R.string.assurance),
    MERCHANT(R.drawable.ic_merchant, R.string.merchant),
    MOBILE_CREDIT(R.drawable.ic_mobile_credit, R.string.mobile_credit),
    BILL(R.drawable.ic_bill, R.string.bill),
    MORE(R.drawable.ic_more, R.string.more)
}