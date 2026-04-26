package com.punyo.portfolio.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.attr
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.A

@Composable
fun ExternalLink(href: String, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    A(
        href = href,
        attrs = modifier
            .attr("target", "_blank")
            .attr("rel", "noopener noreferrer")
            .toAttrs()
    ) {
        content()
    }
}
