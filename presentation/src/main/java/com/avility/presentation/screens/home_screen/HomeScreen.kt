package com.avility.presentation.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.avility.domain.model.CardModel
import com.avility.presentation.R
import com.avility.shared.ui.components.containers.BaseRowItem
import com.avility.shared.ui.components.containers.BasicContainer
import com.avility.shared.ui.components.containers.MainContainer
import com.avility.shared.ui.constants.MeasureLargeDimen
import com.avility.shared.ui.constants.MeasureSmallDimen
import com.avility.shared.ui.constants.dangerColor
import com.avility.shared.ui.constants.roundedShapes
import com.avility.shared.ui.constants.successColor

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.value
    val card = state.data?.card
    val movements = state.data?.movements ?: emptyList()

    MainContainer(
        isLoading = state.isLoading,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.Center
            ) {
                StoriCreditCard(
                    card
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
                    .padding(
                        top = MeasureSmallDimen.DIMEN_X04.value,
                        start = MeasureSmallDimen.DIMEN_X03.value,
                        end = MeasureSmallDimen.DIMEN_X03.value
                    )
                    .background(Color.Transparent)
            ) {
                LazyColumn {
                    items(movements) { movement ->
                        BaseRowItem(
                            leading = {
                                Text(
                                    text = movement.transactionDescription,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            trailing = {
                                Column(
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        text = movement.transactionValue,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = if(movement.natureTransaction) {
                                                successColor
                                            } else {
                                                dangerColor
                                            },
                                            fontWeight = FontWeight.Bold
                                        ),
                                        textAlign = TextAlign.End
                                    )
                                    Text(
                                        text = movement.date,
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.Bold
                                        ),
                                        textAlign = TextAlign.End
                                    )
                                }
                            },
                            leadingWeight = 0.7f,
                            trailingWeight = 0.3f,
                            onTap = {

                            }
                        )
                        Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X03.value))
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StoriCreditCard(
    card: CardModel?
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(MeasureLargeDimen.DIMEN_X20.value),
        shape = roundedShapes.large,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = MeasureSmallDimen.DIMEN_X10.value,
            focusedElevation = MeasureSmallDimen.DIMEN_X10.value,
            disabledElevation = MeasureSmallDimen.DIMEN_X00.value
        ),
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(MeasureSmallDimen.DIMEN_X10.value)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(MeasureLargeDimen.DIMEN_X08.value),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BasicContainer(
                    modifier = Modifier
                        .width(MeasureSmallDimen.DIMEN_X25.value)
                        .height(MeasureSmallDimen.DIMEN_X20.value),
                    containerColor = MaterialTheme.colorScheme.outline,
                    roundedCornerShape = roundedShapes.extraSmall
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.card_chip),
                        contentDescription = "",
                    )
                }
                Column {
                    Image(
                        modifier = Modifier.size(MeasureSmallDimen.DIMEN_X12.value),
                        painter = painterResource(id = R.drawable.nfc_logo),
                        contentDescription = "",
                    )
                    val ccv = card?.ccv?.let {
                        it.toString()
                    } ?: ""
                    Text(text = ccv, style = MaterialTheme.typography.labelMedium)
                }
            }
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = card?.cardNumber.orEmpty(),
                    style = MaterialTheme.typography.displayLarge
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.valid_until),
                            style = MaterialTheme.typography.labelSmall
                        )
                        Text(
                            text = "${card?.monthExpire.orEmpty()} / ${card?.yearExpire.orEmpty()}"
                        )
                    }
                    Text(
                        text = card?.holderName.orEmpty()
                    )
                }
            }
        }
    }
}