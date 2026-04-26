package com.punyo.portfolio.pages

import androidx.compose.runtime.*
import com.punyo.portfolio.components.layouts.PageLayoutData
import com.punyo.portfolio.components.sections.TimelineEntries
import com.punyo.portfolio.components.sections.TimelineEntry
import com.punyo.portfolio.components.sections.TimelineLink
import com.punyo.portfolio.components.sections.TimelineStatus
import com.punyo.portfolio.components.ui.pageTitleModifier
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.attr
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@InitRoute
fun initTimelinePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Timeline"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun TimelinePage() {
    var entries by remember { mutableStateOf<List<TimelineEntry>?>(null) }
    var loadError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        loadError = null
        entries = null
        val response = window.fetch("/timeline.json").await()
        entries = parseTimelineEntries(response.text().await())
    }

    Column(
        Modifier
            .fillMaxWidth()
            .gap(2.cssRem)
            .attr("id", "timeline")
            .attr("aria-label", "Timeline")
    ) {
        H1(pageTitleModifier().toAttrs()) {
            Text("Timeline")
        }
        when {
            loadError != null -> TimelineStatus(loadError!!)
            entries == null -> TimelineStatus("読み込み中")
            else -> TimelineEntries(entries!!)
        }
    }
}

fun parseTimelineEntries(jsonText: String): List<TimelineEntry> {
    val parsed = parseJson(jsonText)
    if (!isJsArray(parsed)) return emptyList()

    return (0 until jsArrayLength(parsed)).mapNotNull { index ->
        val item = parsed[index] ?: return@mapNotNull null
        val title = stringValue(item.title)
        if (title.isBlank()) return@mapNotNull null

        TimelineEntry(
            date = stringValue(item.date),
            title = title,
            summary = stringValue(item.summary),
            points = stringListValue(item.points),
            links = linkListValue(item.links),
        )
    }
}

@Suppress("UNUSED_PARAMETER")
private fun parseJson(jsonText: String): dynamic = js("JSON.parse(jsonText)")

@Suppress("UNUSED_PARAMETER")
private fun isJsArray(value: dynamic): Boolean = js("Array.isArray(value)")

private fun jsArrayLength(value: dynamic): Int = value.length as Int

private fun stringValue(value: dynamic): String {
    return (value as? String)?.trim().orEmpty()
}

private fun stringListValue(value: dynamic): List<String> {
    if (!isJsArray(value)) return emptyList()

    return (0 until jsArrayLength(value)).mapNotNull { index ->
        stringValue(value[index]).takeIf { it.isNotBlank() }
    }
}

private fun linkListValue(value: dynamic): List<TimelineLink> {
    if (!isJsArray(value)) return emptyList()

    return (0 until jsArrayLength(value)).mapNotNull { index ->
        val item = value[index] ?: return@mapNotNull null
        val label = stringValue(item.label)
        val url = stringValue(item.url)

        if (label.isBlank() || url.isBlank()) {
            null
        } else {
            TimelineLink(label = label, url = url)
        }
    }
}
