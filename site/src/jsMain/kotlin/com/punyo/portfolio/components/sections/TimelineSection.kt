package com.punyo.portfolio.components.sections

import androidx.compose.runtime.Composable
import com.punyo.portfolio.components.ui.cardModifier
import com.punyo.portfolio.components.ui.cardTitleModifier
import com.punyo.portfolio.components.ui.linkTextModifier
import com.punyo.portfolio.components.widgets.ExternalLink
import com.punyo.portfolio.toSitePalette
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

data class TimelineLink(
    val label: String,
    val url: String,
)

data class TimelineEntry(
    val date: String,
    val title: String,
    val summary: String,
    val points: List<String>,
    val links: List<TimelineLink>,
)

@Composable
fun TimelineEntries(entries: List<TimelineEntry>) {
    Column(
        Modifier
            .fillMaxWidth()
            .gap(0.cssRem)
    ) {
        entries.forEachIndexed { index, entry ->
            TimelineItem(entry, isLast = index == entries.lastIndex)
        }
    }
}

@Composable
private fun TimelineItem(entry: TimelineEntry, isLast: Boolean) {
    Row(
        Modifier
            .fillMaxWidth()
            .gap(1.cssRem),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            Modifier
                .width(1.5.cssRem)
                .minWidth(1.5.cssRem)
                .alignSelf(AlignSelf.Stretch),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Div(
                Modifier
                    .size(0.85.cssRem)
                    .borderRadius(50.cssRem)
                    .backgroundColor(ColorMode.current.toSitePalette().brand.accent)
                    .border(3.px, LineStyle.Solid, ColorMode.current.toPalette().color.toRgb().copyf(alpha = 0.12f))
                    .toAttrs()
            )
            if (!isLast) {
                Div(
                    Modifier
                        .width(2.px)
                        .flexGrow(1)
                        .minHeight(2.5.cssRem)
                        .margin(top = 0.35.cssRem)
                        .backgroundColor(ColorMode.current.toSitePalette().cobweb)
                        .toAttrs()
                )
            }
        }

        Column(cardModifier(ColorMode.current).flexGrow(1).minWidth(0.px).margin(bottom = 1.6.cssRem)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .gap(0.5.cssRem)
                    .flexWrap(FlexWrap.Wrap),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SpanText(
                    entry.date,
                    Modifier
                        .fontSize(0.92.cssRem)
                        .fontWeight(FontWeight.Bolder)
                        .color(ColorMode.current.toSitePalette().brand.primary)
                )
            }

            Div(cardTitleModifier().margin(0.px).toAttrs()) {
                Text(entry.title)
            }

            if (entry.summary.isNotBlank()) {
                P(
                    Modifier
                        .margin(0.px)
                        .fontSize(0.96.cssRem)
                        .color(ColorMode.current.toPalette().color.toRgb().copyf(alpha = 0.82f))
                        .toAttrs()
                ) {
                    Text(entry.summary)
                }
            }

            if (entry.points.isNotEmpty()) {
                Ul(
                    Modifier
                        .margin(0.px)
                        .fontSize(0.94.cssRem)
                        .color(ColorMode.current.toPalette().color.toRgb().copyf(alpha = 0.84f))
                        .toAttrs()
                ) {
                    entry.points.forEach { point ->
                        Li {
                            Text(point)
                        }
                    }
                }
            }

            if (entry.links.isNotEmpty()) {
                Row(
                    Modifier
                        .gap(0.8.cssRem)
                        .flexWrap(FlexWrap.Wrap),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    entry.links.forEach { link ->
                        ExternalLink(href = link.url, modifier = linkTextModifier(ColorMode.current)) {
                            Text(link.label)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimelineStatus(message: String) {
    Div(
        Modifier
            .padding(1.cssRem)
            .borderRadius(0.5.cssRem)
            .backgroundColor(ColorMode.current.toSitePalette().nearBackground)
            .border(1.px, LineStyle.Solid, ColorMode.current.toSitePalette().cobweb)
            .color(ColorMode.current.toPalette().color.toRgb().copyf(alpha = 0.78f))
            .toAttrs()
    ) {
        Text(message)
    }
}
