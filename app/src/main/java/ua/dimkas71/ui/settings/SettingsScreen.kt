package ua.dimkas71.ui.settings

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ua.dimkas71.common.PASSWORD_KEY
import ua.dimkas71.common.SEARCH_VARIANT_KEY
import ua.dimkas71.common.START_DATE_BY_DEFAULT_KEY
import ua.dimkas71.common.SearchVariant

import ua.dimkas71.common.URI_KEY
import ua.dimkas71.common.USER_KEY
import ua.dimkas71.common.getSharedPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onUpClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    val prefManager = getSharedPreferences(LocalContext.current)

    val uriTextValue = remember {
        mutableStateOf(prefManager.getString(URI_KEY, "")?.let { TextFieldValue(it) }
            ?: TextFieldValue(""))
    }

    val userTextValue = remember {
        mutableStateOf(prefManager.getString( USER_KEY,"")?.let { TextFieldValue(it) }
            ?: TextFieldValue(""))
    }

    val passwordTextValue = remember {
        mutableStateOf(prefManager.getString(PASSWORD_KEY, "")?.let { TextFieldValue(it) }
            ?: TextFieldValue(""))
    }

    val searchOptions = enumValues<SearchVariant>()

    val variantIndex = prefManager.getInt(SEARCH_VARIANT_KEY, 0) ?: 0

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(searchOptions[variantIndex])
    }

    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input,
        initialSelectedDateMillis = prefManager.getLong(START_DATE_BY_DEFAULT_KEY, 0L)?.let {
            it
        } ?: 0L
    )

    DisposableEffect(
        uriTextValue,
        userTextValue,
        passwordTextValue,
        datePickerState,
        selectedOption
    ) {
        onDispose {
            prefManager.edit()
                .apply() {
                    putString(URI_KEY, uriTextValue.value.text)
                    putString(USER_KEY, userTextValue.value.text)
                    putString(PASSWORD_KEY, passwordTextValue.value.text)
                    putLong(START_DATE_BY_DEFAULT_KEY, datePickerState.selectedDateMillis ?: 0L)
                    putInt(SEARCH_VARIANT_KEY, selectedOption.ordinal)
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
    ) { contentPadding ->
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
                    label = { Text("User:") },
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
                Text(
                    "Search variant:",
                    style = MaterialTheme.typography.bodyLarge.merge(),
                    modifier = Modifier.padding(start = 8.dp)
                )
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
                            verticalAlignment = Alignment.CenterVertically
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
                Divider(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .height(8.dp)
                )
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.padding(8.dp),
                    title = { Text("Begin date for report") },
                )

            }
        }
    }


}