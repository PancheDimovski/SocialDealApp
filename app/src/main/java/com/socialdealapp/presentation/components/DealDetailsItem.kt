package com.socialdealapp.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.socialdealapp.domain.dealDescriptionDetails.model.DealItemDescriptionData
import com.socialdealapp.utils.Dimensions
import com.socialdealapp.utils.toPlainText

@Composable
fun DealDetailsItem(
    data: DealItemDescriptionData,
    currencySymbol: String,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(Dimensions.dp20))
    Text(
        text = "Deals Details:",
        style = MaterialTheme.typography.h5,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(Dimensions.dp8))
    data.descTitle?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.h6,
            color = Color.DarkGray
        )
    }
    Spacer(modifier = Modifier.height(Dimensions.dp4))
    Text(
        text = data.description.toPlainText(),
        style = MaterialTheme.typography.body1,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(Dimensions.dp8))
    Text(
        text = "Additional Details",
        style = MaterialTheme.typography.h5,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(Dimensions.dp8))
    Text(
        text = "City: ${data.descCity}",
        style = MaterialTheme.typography.body2,
        color = Color.Gray
    )
    Text(
        text = "Label:${data.descSoldLabel}",
        style = MaterialTheme.typography.body2,
        color = Color.Gray
    )
    Text(
        text = "Price: $currencySymbol" +
                "${data.descPrice}",
        style = MaterialTheme.typography.body1,
        color = Color.Red
    )
    Text(
        text = "From Price: $currencySymbol" +
                "${data.fromPrice}",
        style = MaterialTheme.typography.body2,
        color = Color.Gray,
        textDecoration = TextDecoration.LineThrough,
    )
}