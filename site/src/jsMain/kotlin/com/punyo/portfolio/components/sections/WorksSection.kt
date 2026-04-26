package com.punyo.portfolio.components.sections

import androidx.compose.runtime.Composable
import com.punyo.portfolio.components.ui.cardModifier
import com.punyo.portfolio.components.ui.cardTitleModifier
import com.punyo.portfolio.components.ui.linkTextModifier
import com.punyo.portfolio.components.widgets.ExternalLink
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

sealed class WorkMedia {
    data class Image(val src: String, val alt: String = "") : WorkMedia()
    data class Video(val src: String) : WorkMedia()
}

data class WorkLink(
    val label: String,
    val url: String,
    val isExternal: Boolean = true,
)

class WorkProject(
    val name: String,
    val media: List<WorkMedia>,
    val description: @Composable () -> Unit,
    val techStack: List<String>,
    val links: List<WorkLink>,
)

data class WorkYearSection(
    val year: String,
    val projects: List<WorkProject>,
)

private fun worksSectionHeadingModifier(): Modifier {
    return Modifier
        .margin(top = 1.cssRem, bottom = 0.25.cssRem)
        .fontSize(1.cssRem)
        .fontWeight(FontWeight.Bolder)
}

@Composable
fun WorkProjectCard(project: WorkProject) {
    Column(cardModifier(ColorMode.current).fillMaxWidth().margin(bottom = 1.2.cssRem)) {
        H3(cardTitleModifier().margin(top = 0.px, bottom = 0.5.cssRem).toAttrs()) {
            Text(project.name)
        }

        if (project.media.isNotEmpty()) {
            WorkMediaRow(project.media)
        }

        H4(worksSectionHeadingModifier().toAttrs()) { Text("概要") }
        project.description()

        H4(worksSectionHeadingModifier().toAttrs()) { Text("使用技術") }
        Ul(Modifier.margin(top = 0.px, bottom = 0.px).toAttrs()) {
            project.techStack.forEach { tech ->
                Li { Text(tech) }
            }
        }

        H4(worksSectionHeadingModifier().toAttrs()) { Text("リンク") }
        Ul(Modifier.margin(top = 0.px, bottom = 0.px).toAttrs()) {
            project.links.forEach { link ->
                Li {
                    when {
                        link.isExternal -> {
                            ExternalLink(href = link.url, modifier = linkTextModifier(ColorMode.current)) {
                                Text(link.label)
                            }
                        }

                        link.url.isNotEmpty() -> {
                            Link(link.url, link.label, linkTextModifier(ColorMode.current))
                        }

                        else -> {
                            SpanText(
                                link.label,
                                Modifier
                                    .color(ColorMode.current.toPalette().color.toRgb().copyf(alpha = 0.5f))
                                    .textDecorationLine(TextDecorationLine.LineThrough)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WorkMediaRow(media: List<WorkMedia>) {
    Div(
        Modifier
            .display(DisplayStyle.Flex)
            .flexWrap(FlexWrap.Wrap)
            .gap(0.5.cssRem)
            .alignItems(AlignItems.Center)
            .margin(bottom = 0.5.cssRem)
            .toAttrs()
    ) {
        media.forEach { item ->
            when (item) {
                is WorkMedia.Video -> {
                    Video(
                        attrs = {
                            attr("src", item.src)
                            attr("controls", "")
                            style { property("width", "180px") }
                        }
                    )
                }

                is WorkMedia.Image -> {
                    Image(
                        src = item.src,
                        alt = item.alt,
                        modifier = Modifier.maxWidth(180.px),
                    )
                }
            }
        }
    }
}
