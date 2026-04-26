package com.punyo.portfolio.components.ui

import com.punyo.portfolio.toSitePalette
import com.varabyte.kobweb.compose.css.OverflowWrap
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import org.jetbrains.compose.web.css.*

fun pageTitleModifier(): Modifier = Modifier
    .margin(0.px)
    .fontSize(2.2.cssRem)
    .fontWeight(600)
    .lineHeight(1.15)

fun cardModifier(colorMode: ColorMode): Modifier = Modifier
    .padding(1.15.cssRem)
    .borderRadius(0.5.cssRem)
    .backgroundColor(colorMode.toSitePalette().nearBackground)
    .border(1.px, LineStyle.Solid, colorMode.toSitePalette().cobweb)
    .boxShadow(
        offsetX = 0.px,
        offsetY = 0.45.cssRem,
        blurRadius = 1.1.cssRem,
        color = colorMode.toPalette().color.toRgb().copyf(alpha = 0.08f)
    )
    .gap(0.75.cssRem)
    .overflowWrap(OverflowWrap.BreakWord)

fun linkTextModifier(colorMode: ColorMode): Modifier = Modifier
    .color(colorMode.toSitePalette().brand.accent)
    .fontWeight(650)

fun cardTitleModifier(): Modifier = Modifier
    .fontSize(1.3.cssRem)
    .fontWeight(650)
    .lineHeight(1.3)
