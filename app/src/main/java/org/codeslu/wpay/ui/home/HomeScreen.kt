package org.codeslu.wpay.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.Promo
import org.codeslu.wpay.ui.home.components.PaymentListSection
import org.codeslu.wpay.ui.home.components.PromoAndDiscountsSection
import org.codeslu.wpay.ui.home.components.UserBalanceSection
import org.codeslu.wpay.ui.theme.WPayTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = androidx.compose.ui.platform.LocalContext.current
    var snackbarMessage by remember {
        mutableStateOf("")
    }
    HomeScreenContent(
        modifier = modifier,
        firstName = state.currentUser.firstName,
        availableBalance = state.availableBalance,
        promos = state.promos,
        snackbarMessage = snackbarMessage,
        onAction = { action ->
            when (action) {
                HomeUiAction.OnSettingsClicked -> {
                    snackbarMessage = "Settings clicked"
                }

                HomeUiAction.OnHistoryClicked -> {
                    snackbarMessage = "History clicked"
                }

                is HomeUiAction.OnOptionClicked -> {
                    snackbarMessage = "${context.getString(action.optionTitleRes)} clicked"
                }

                HomeUiAction.OnSeeMoreClicked -> {
                    snackbarMessage = "See more clicked"
                }

                HomeUiAction.OnTopUpClicked -> {
                    snackbarMessage = "Top Up clicked"
                }

                HomeUiAction.OnTransferClicked -> {
                    snackbarMessage = "Transfer clicked"
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    firstName: String = "",
    availableBalance: Double = 0.0,
    promos: List<Promo> = emptyList(),
    snackbarMessage: String = "",
    onAction: (HomeUiAction) -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(snackbarMessage) {
        if (snackbarMessage.isNotBlank()) {
            snackbarHostState.showSnackbar(snackbarMessage)
        }
    }
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                title = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = stringResource(R.string.app_logo),
                        tint = Color.Unspecified,
                        modifier = Modifier.size(width = 64.dp, height = 38.dp)
                    )
                },
                actions = {
                    OutlinedIconButton(
                        onClick = { onAction(HomeUiAction.OnSettingsClicked) },
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(width = 2.dp, color = Color.Black.copy(alpha = 0.1f)),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_settings),
                            contentDescription = "Settings",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            UserBalanceSection(
                modifier = Modifier.padding(horizontal = 16.dp),
                firstName = firstName,
                balance = availableBalance,
                onTransferClicked = { onAction(HomeUiAction.OnTransferClicked) },
                onTopUpClicked = { onAction(HomeUiAction.OnTopUpClicked) },
                onHistoryClicked = { onAction(HomeUiAction.OnHistoryClicked) }
            )
            PaymentListSection(
                modifier = Modifier.padding(horizontal = 16.dp),
                onOptionClicked = { res -> onAction(HomeUiAction.OnOptionClicked(res)) }
            )
            PromoAndDiscountsSection(
                promos = promos,
                onSeeMoreClicked = { onAction(HomeUiAction.OnSeeMoreClicked) }
            )
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenContentPreview() {
    WPayTheme {
        HomeScreenContent(
            firstName = "Andre",
            availableBalance = 15901.0,
            promos = listOf(
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
        )
    }
}