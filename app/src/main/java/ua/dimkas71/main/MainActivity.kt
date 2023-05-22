package ua.dimkas71.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.dimkas71.ui.theme.ReadingsMetersOfElectricityTheme



class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            ReadingsMetersOfElectricityTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        MainScreen(navController)
                    }
                    composable("settings") {
                        SettingsScreen(
                            onUpClick = {
                                navController.navigateUp()
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MeterScreen(meterInfo: MeterInfo? = null, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.outlinedCardElevation(16.dp),
        shape = MaterialTheme.shapes.large

    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("№ ліч.: 0754410ліч-к №2", style = MaterialTheme.typography.titleMedium)
        }
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Поточний показник: 32324",
                    style = MaterialTheme.typography.bodyMedium
                    )
                Text("Попередній показник: 32320",
                style = MaterialTheme.typography.bodyMedium)
                Text("Спожито: 4(кВт)",
                style = MaterialTheme.typography.bodyMedium)


            }
            Column(
                modifier = Modifier.padding(end = 16.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.End
            ) {
                Text("До сплати:", style = MaterialTheme.typography.bodyMedium)
                Text(
                    "1 400.32 грн.",
                style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.error)
            }

        }

        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Text("№ дог.: 12367", style = MaterialTheme.typography.bodySmall)
            Text("Ост.пов.: 01.05.2021", style = MaterialTheme.typography.bodySmall)
            Icon(
                painter = painterResource(id = R.drawable.lock_24),
                contentDescription = null
            )
        }
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.history_24),
                    contentDescription = "Історія"
                )
            }
            Button(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.payment_24),
                    contentDescription = "Оплати"
                )
            }

            Button(
                onClick = { /*TODO*/ },
                ) {
                Icon(
                    painter = painterResource(id = R.drawable.contact_phone_24),
                    contentDescription = null
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContractScreen(contractInfo: ContractInfo? = null, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),

        //elevation = CardDefaults.elevatedCardElevation(16.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                "62 С = 4769/1тс")
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                "Бабій Ярослав Іванович")
        }
        Column {
            MeterScreen()
            Spacer(modifier = Modifier.height(4.dp))
            MeterScreen()
            Spacer(modifier = Modifier.height(4.dp))
            MeterScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReadingsMetersOfElectricityTheme {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var searchQuery by remember {
        mutableStateOf(TextFieldValue())
    }
    if (isExpanded) {
        TextField(
            value = searchQuery,
            onValueChange = { newValue ->
                searchQuery = newValue
            },
            modifier = modifier,
            placeholder = {
                Text("Search hint: 145 or 112")
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        isExpanded = false
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = androidx.compose.ui.text.input.ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch(searchQuery.text)
                searchQuery = TextFieldValue()
                isExpanded = false
            })

        )

    } else {
        IconButton(onClick = {
            isExpanded = true
        }) {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen(
        navController: NavController,
        modifier: Modifier = Modifier
    ) {
        // A surface container using the 'background' color from the theme
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Electricity meters") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,

                        ),
                    actions = {
                        SearchField(
                            onSearch = { searchQuery ->
                                Log.d("MainActivity4", "You entered query: $searchQuery")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        IconButton(
                            onClick = {
                                navController.navigate("settings")
                            },
                        ) {
                            Icon(
                                Icons.Default.Settings,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }

                )
            }

        ) { contentPadding ->
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier.padding(contentPadding)
            ) {
                items(10) { _ ->
                    ContractScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onUpClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    val prefManager = LocalContext.current.getSharedPreferences("${LocalContext.current.applicationInfo.name}.prefs", Context.MODE_PRIVATE)
    val uriTextValue = remember {
        mutableStateOf(prefManager.getString("URI_KEY", "")?.let { TextFieldValue(it) } ?: TextFieldValue(""))
    }

    val userTextValue = remember {
        mutableStateOf(prefManager.getString("USER_KEY", "")?.let{TextFieldValue(it)} ?: TextFieldValue(""))
    }

    val passwordTextValue = remember {
        mutableStateOf(prefManager.getString("PASSWORD_KEY", "")?.let{TextFieldValue(it)} ?: TextFieldValue(""))
    }

    val searchOptions = enumValues<SearchVariant>()

    val variantIndex = prefManager.getInt("SEARCH_VARIANT_KEY", 0) ?: 0

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(searchOptions[variantIndex])
    }

    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input,
        initialSelectedDateMillis = prefManager.getLong("START_DATE_BY_DEFAULT_KEY", 0L)?.let {
            it
        } ?: 0L
    )

    DisposableEffect(uriTextValue, userTextValue, passwordTextValue, datePickerState, selectedOption) {
        onDispose {
            prefManager.edit()
                .apply() {
                    putString("URI_KEY", uriTextValue.value.text)
                    putString("USER_KEY", userTextValue.value.text)
                    putString("PASSWORD_KEY", passwordTextValue.value.text)
                    putLong("START_DATE_BY_DEFAULT_KEY", datePickerState.selectedDateMillis ?: 0L)
                    putInt("SEARCH_VARIANT_KEY", selectedOption.ordinal)
                    commit()
                }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
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
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,

                    ),
            )
        }
    ) {contentPadding ->
        Box(
            modifier = modifier.padding(contentPadding)
        ) {
           Column(
               modifier = Modifier.verticalScroll(rememberScrollState())
           ) {
               TextField(
                   value = uriTextValue.value,
                   onValueChange = { it ->
                       uriTextValue.value = it
                   },
                   label = {
                       Text("Connection Uri:")
                   },
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(8.dp)
               )
               TextField(
                   value = userTextValue.value,
                   onValueChange = {
                    userTextValue.value = it
               },
                   label = {Text("User:")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                   )
               TextField(
                   value = passwordTextValue.value,
                   onValueChange = {
                       passwordTextValue.value = it
                   },
                    label = {
                        Text("Password")
                    },
                   visualTransformation = PasswordVisualTransformation(),
                   keyboardOptions = KeyboardOptions(
                       keyboardType = KeyboardType.Password
                   ),
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(8.dp)
                   )
               Divider(
                   modifier = Modifier
                       .padding(start = 8.dp, end = 8.dp)
                       .height(8.dp)
               )
               Text("Search variant:", style = MaterialTheme.typography.bodyLarge.merge(), modifier = Modifier.padding(start = 8.dp))
               Column(modifier = Modifier.selectableGroup()) {
                    searchOptions.forEach { variant ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(32.dp)
                                .selectable(
                                    selected = (variant == selectedOption),
                                    onClick = {
                                        onOptionSelected(variant)
                                              },
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = CenterVertically
                        ) {
                            RadioButton(
                                selected = (variant == selectedOption),
                                onClick = null
                            )
                            Text(
                                text = variant.text,
                                style = MaterialTheme.typography.bodyMedium.merge(),
                                modifier = Modifier.padding(start = 6.dp)
                            )
                        }
                    }
               }
               Divider(modifier = Modifier
                   .padding(start = 8.dp, end = 8.dp)
                   .height(8.dp))
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.padding(8.dp),
                    title = {Text("Begin date for report")},
                    )

           }
        }
    }


}