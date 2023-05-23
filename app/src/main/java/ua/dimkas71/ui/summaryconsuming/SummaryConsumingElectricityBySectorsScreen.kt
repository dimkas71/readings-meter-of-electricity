package ua.dimkas71.ui.summaryconsuming

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryConsumingElectricityBySectorsScreen(
    onUpClick: () -> Unit,
    viewModel: SummaryConsumingElectricityBySectorsViewModel = SummaryConsumingElectricityBySectorsViewModel(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Summary consuming electricity by sectors") },
                navigationIcon = {
                    IconButton(onClick = onUpClick) {
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
        Box(modifier = modifier.padding(contentPadding)) {
            Text("Meters by sectors screen")

            Log.d("SummaryScreen", viewModel.demo(LocalContext.current).toString())
        }
    }
}