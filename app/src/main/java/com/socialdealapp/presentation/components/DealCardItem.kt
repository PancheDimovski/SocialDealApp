package com.socialdealapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.ui.theme.BlueLight
import com.socialdealapp.ui.theme.GreenLight

@Composable
fun DealCardItem(
    data: DealsModel,
    onToggleFavorite: (String, Boolean) -> Unit,
    currencySymbol: String,
    modifier: Modifier = Modifier,
    onNavigate: ((String) -> Unit)? = null,
) {
    var clickableModifier = modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(8.dp))
        .fillMaxWidth()
    clickableModifier = if (onNavigate != null) {
        clickableModifier
            .clickable(onClick = { onNavigate(data.uniqueId) })
    } else clickableModifier
    Card(
        modifier = clickableModifier,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(Modifier.padding(12.dp)) {
            Box(contentAlignment = Alignment.BottomEnd) {
                SdImage(imageUrl = data.imageUrl)
                FavoriteButton(
                    addDealToFavorite = onToggleFavorite,
                    uniqueId = data.uniqueId,
                    isFavorite = data.isAddedToFavorite
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.title,
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
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
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = data.soldLabel,
                    style = MaterialTheme.typography.body2,
                    color = BlueLight
                )
                Row(horizontalArrangement = Arrangement.SpaceBetween) {

                    Text(
                        text = currencySymbol,
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.LineThrough,
                    )

                    data.originalPrice?.let {
                        Text(
                            text = it.toString(),
                            style = MaterialTheme.typography.body2,
                            color = Color.Gray,
                            fontSize = 14.sp,
                            textDecoration = TextDecoration.LineThrough,
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = currencySymbol,
                        style = MaterialTheme.typography.body2,
                        fontSize = 16.sp,
                        color = GreenLight
                    )
                    data.fromPrice?.let {
                        Text(
                            text = it.toString(),
                            style = MaterialTheme.typography.body2,
                            fontSize = 16.sp,
                            color = GreenLight
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DealCardItemPreview() {
    MaterialTheme {
        DealCardItem(
            data = DealsModel(
                uniqueId = "12345",
                title = "Amazing Headphones",
                imageUrl = "https://i.sstatic.net/9Sg52.png",
                companyName = "Company",
                city = "New York",
                soldLabel = "Sold!",
                symbol = "$",
                originalPrice = 1000,
                fromPrice = 500,
                discountLabel = "label",
                currencyCode = "Euro",
                isAddedToFavorite = true
            ),
            onNavigate = {},
            onToggleFavorite = { _, _ -> },
            currencySymbol = "Euro"
        )
    }
}