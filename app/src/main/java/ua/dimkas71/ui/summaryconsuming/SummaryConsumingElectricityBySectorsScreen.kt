package ua.dimkas71.ui.summaryconsuming

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.dimkas71.ui.theme.ReadingsMetersOfElectricityTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SummaryConsumingElectricityBySectorsScreen(
    viewModel: SummaryConsumingElectricityBySectorsViewModel,
    onUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {


    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input,
        initialSelectedDateMillis = 0L)



    ReadingsMetersOfElectricityTheme(

    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Summary consuming electricity by sectors") },
                    navigationIcon = {
                        IconButton(onClick = onUpClick ) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = null

                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )


                )
            }

        ) { contentPadding ->
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)) {
                item {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DatePicker(
                            state = datePickerState,
                            modifier = Modifier.padding(8.dp),
                            title = { Text("Report date") },
                        )
                        Divider(
                            modifier = Modifier
                                .padding(8.dp)
                                .height(4.dp)
                        )
                    }
                }
                items(viewModel.uiState.items) {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Sect: ${it.sector}")
                        Text("${it.consumed} (kWt)", style = MaterialTheme.typography.titleMedium)
                    }
                }
                item {
                    Divider(
                        modifier = Modifier
                            .padding(8.dp)
                            .height(4.dp)
                    )
                }
                item {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Total:",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Text("${viewModel.uiState.items.sumOf{it.consumed} / 100.0 * 100} (kWt)",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.error
                            )
                    }
                }
            }
            SideEffect {
                Log.d("Data changed", datePickerState.selectedDateMillis.toString())
            }
        }
    }
}