package ua.dimkas71.ui.summaryconsuming

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.dimkas71.common.START_DATE_BY_DEFAULT_KEY
import ua.dimkas71.data.SummaryConsumingBySectors
import ua.dimkas71.ui.theme.ReadingsMetersOfElectricityTheme
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SummaryConsumingElectricityBySectorsPreview(
    list: List<SummaryConsumingBySectors> = (1..8).map {
        SummaryConsumingBySectors(
            it,
            Random.nextLong(1_000, 100_000))
    },
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
                        IconButton(onClick = { }) {
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
                            modifier = Modifier.padding(8.dp).height(4.dp)
                        )
                    }
                }
                items(list) {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Sect: ${it.sector}")
                        Text("${it.consumed} (kWt)", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}

