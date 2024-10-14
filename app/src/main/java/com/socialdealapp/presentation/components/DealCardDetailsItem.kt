package com.socialdealapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.socialdealapp.domain.dealDescriptionDetails.model.DealItemDescriptionData
import com.socialdealapp.ui.theme.BlueLight
import com.socialdealapp.ui.theme.GreenLight
import com.socialdealapp.utils.Dimensions

@Composable
fun DealCardDetailsItem(
    data: DealItemDescriptionData,
    currencySymbol: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(Dimensions.dp8)
    ) {
        SdImage(imageUrl = data.imageUrl)
    }
    Spacer(modifier = Modifier.height(Dimensions.dp16))
    Text(
        text = data.title,
        style = MaterialTheme.typography.h6,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(Dimensions.dp4))
    Text(
        text = data.companyName,
        style = MaterialTheme.typography.body2,
        color = Color.Gray
    )
    Text(
        text = data.city,
        style = MaterialTheme.typography.body2,
        color = Color.Gray
    )

    Spacer(modifier = Modifier.height(Dimensions.dp8))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = data.soldLabel,
            style = MaterialTheme.typography.body2,
            color = BlueLight
        )
        Row {
            Text(
                text = currencySymbol,
                style = MaterialTheme.typography.body2,
                color = Color.Gray,
                fontSize = Dimensions.sp14,
                textDecoration = TextDecoration.LineThrough,
            )
            data.originalPrice?.let {
                Text(
                    text = it.toString(),
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray,
                    fontSize = Dimensions.sp14,
                    textDecoration = TextDecoration.LineThrough,
                )
            }
            Spacer(modifier = Modifier.width(Dimensions.dp16))
            Text(
                text = currencySymbol,
                style = MaterialTheme.typography.body2,
                fontSize = Dimensions.sp16,
                color = GreenLight
            )
            data.fromPrice?.let {
                Text(
                    text = it.toString(),
                    style = MaterialTheme.typography.body2,
                    fontSize = Dimensions.sp16,
                    color = GreenLight
                )
            }
        }
    }
}