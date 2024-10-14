package com.socialdealapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.socialdealapp.domain.currency.model.CurrencySymbol

@SuppressLint("RememberReturnType")
@Composable
fun CurrencyToggle(
    isChecked: Boolean,
    currencySymbol: CurrencySymbol,
    onCheckChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Choose currency:",
                style = MaterialTheme.typography.h4,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Text(
                text = "Dollar or Euro",
                style = MaterialTheme.typography.h5,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Switch(
                checked = isChecked,
                onCheckedChange = { isChecked ->
                    onCheckChanged(isChecked)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Blue,
                    uncheckedThumbColor = Color.Blue,
                    checkedTrackColor = Color.Gray,
                    uncheckedTrackColor = Color.Gray
                )
            )
            Text(
                text = currencySymbol.symbol,
                style = MaterialTheme.typography.h5,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyTogglePreview() {
    MaterialTheme {
        CurrencyToggle(modifier = Modifier, isChecked = false,
            currencySymbol = CurrencySymbol.EURO,
            onCheckChanged = {})
    }
}