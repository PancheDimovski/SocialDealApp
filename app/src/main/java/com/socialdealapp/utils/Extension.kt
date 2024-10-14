package com.socialdealapp.utils

import androidx.core.text.HtmlCompat

fun String.toPlainText(): String {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}