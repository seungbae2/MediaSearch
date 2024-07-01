package com.sb.mediasearch.feature.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sb.mediasearch.core.common.instant.formatToDateTimeString
import com.sb.mediasearch.core.designsystem.component.DynamicAsyncImage
import com.sb.mediasearch.core.designsystem.icon.MsIcons
import com.sb.mediasearch.core.model.Content

@Composable
fun ContentItem(content: Content, onClickBookmark: (Content) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        DynamicAsyncImage(
            imageUrl = content.thumbnailUrl,
        )
        Text(
            text = content.datetime.formatToDateTimeString(),
            style = MaterialTheme.typography.bodySmall.copy(color = Color.White),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(4.dp)
        )
        Icon(
            imageVector = if (content.isBookmarked) MsIcons.Bookmark else MsIcons.BookmarkBorder,
            contentDescription = null,
            tint = Color.Yellow,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clickable { onClickBookmark(content) }
        )

        if (content.type == "video") {
            Icon(
                imageVector = MsIcons.video ,
                contentDescription = "video Content Tag",
                tint = Color.Red,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            )
        }
    }
}