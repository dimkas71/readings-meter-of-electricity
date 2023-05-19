package ua.dimkas71.main

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardScreen(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    val isPressed by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
               text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description
            )

            Row(
                horizontalArrangement = Arrangement.End
            ) {
                IconToggleButton(
                    checked = isPressed,
                    onCheckedChange = {
                        isPressed != isPressed
                    },

                    ) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = null
                    )
                }
            }

        }
    }




}