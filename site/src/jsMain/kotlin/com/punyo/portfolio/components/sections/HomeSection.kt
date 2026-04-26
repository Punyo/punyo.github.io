package com.punyo.portfolio.components.sections

import androidx.compose.runtime.Composable
import com.punyo.portfolio.components.ui.cardModifier
import com.punyo.portfolio.components.ui.linkTextModifier
import com.punyo.portfolio.components.ui.pageTitleModifier
import com.punyo.portfolio.components.widgets.ExternalLink
import com.punyo.portfolio.toSitePalette
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

data class HomeSocialLink(
    val label: String,
    val href: String,
)

data class HomeSkillCategory(
    val title: String,
    val iconsSrc: String,
)

data class HomeCertification(
    val name: String,
    val date: String,
)

data class HomeLanguageStatImage(
    private val srcTemplate: String,
) {
    fun src(textColor: String): String = srcTemplate.replace("{textColor}", textColor)
}

val HeroSectionStyle = CssStyle {
    base {
        Modifier
            .fillMaxWidth()
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .alignItems(AlignItems.Center)
            .gap(1.5.cssRem)
    }
    Breakpoint.MD {
        Modifier
            .flexDirection(FlexDirection.Row)
            .alignItems(AlignItems.Center)
            .gap(2.5.cssRem)
    }
}

@Composable
fun BioSection(
    name: String,
    affiliation: String,
    message: String,
    avatarSrc: String,
    wakaTimeProfileUrl: String,
    wakaTimeBadgeSrc: String,
    socialLinks: List<HomeSocialLink>,
) {
    val colorMode = ColorMode.current
    Row(
        HeroSectionStyle.toModifier(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            src = avatarSrc,
            modifier = Modifier
                .size(8.cssRem)
                .minWidth(8.cssRem)
                .borderRadius(50.percent)
                .overflow(Overflow.Hidden)
                .border(3.px, LineStyle.Solid, colorMode.toSitePalette().brand.accent)
        )

        Column(
            Modifier.gap(0.6.cssRem),
            horizontalAlignment = Alignment.Start
        ) {
            H1(pageTitleModifier().toAttrs()) {
                Text(name)
            }
            P(
                Modifier
                    .margin(0.px)
                    .fontSize(1.05.cssRem)
                    .color(colorMode.toSitePalette().brand.primary)
                    .toAttrs()
            ) {
                Text(affiliation)
            }
            P(
                Modifier
                    .margin(0.px)
                    .fontSize(0.95.cssRem)
                    .color(colorMode.toPalette().color.toRgb().copyf(alpha = 0.75f))
                    .toAttrs()
            ) {
                Text(message)
            }

            Row(Modifier.gap(0.5.cssRem).flexWrap(FlexWrap.Wrap), verticalAlignment = Alignment.CenterVertically) {
                Link(path = wakaTimeProfileUrl) {
                    Image(
                        src = wakaTimeBadgeSrc,
                        modifier = linkTextModifier(colorMode).display(DisplayStyle.Block)
                    )
                }
                socialLinks.forEach { link ->
                    ExternalLink(
                        href = link.href,
                        modifier = linkTextModifier(colorMode)
                    ) {
                        Text(link.label)
                    }
                }
            }
        }
    }
}

@Composable
fun SkillsSection(categories: List<HomeSkillCategory>) {
    HomeSection(title = "技術") {
        categories.forEach { category ->
            SkillCategoryGrid {
                SkillCategoryCard(title = category.title) {
                    Image(
                        src = category.iconsSrc,
                        modifier = Modifier.fillMaxWidth().display(DisplayStyle.Block),
                    )
                }
            }
        }
    }
}

@Composable
fun LanguageStatsSection(statImages: List<HomeLanguageStatImage>) {
    val colorMode = ColorMode.current
    HomeSection(title = "使用言語統計") {
        Div(
            Modifier
                .display(DisplayStyle.Flex)
                .flexWrap(FlexWrap.Wrap)
                .alignItems(AlignItems.Stretch)
                .gap(1.cssRem)
                .fillMaxWidth()
                .toAttrs()
        ) {
            statImages.forEach { statImage ->
                StatImageCard(
                    src = statImage.src(statsTextColor(colorMode)),
                    colorMode = colorMode,
                )
            }
        }
    }
}

@Composable
fun CertificationSection(certifications: List<HomeCertification>) {
    val colorMode = ColorMode.current
    HomeSection(title = "保有資格") {
        Div(
            cardModifier(colorMode)
                .fillMaxWidth()
                .toAttrs()
        ) {
            Table(Modifier.fillMaxWidth().toAttrs()) {
                Thead {
                    Tr {
                        Th(Modifier.padding(0.4.cssRem).textAlign(TextAlign.Start).toAttrs()) { Text("資格名") }
                        Th(Modifier.padding(0.4.cssRem).textAlign(TextAlign.Start).toAttrs()) { Text("取得時期") }
                    }
                }
                Tbody {
                    certifications.forEach { certification ->
                        Tr {
                            Td(Modifier.padding(0.4.cssRem).toAttrs()) { Text(certification.name) }
                            Td(Modifier.padding(0.4.cssRem).toAttrs()) { Text(certification.date) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeSection(title: String, content: @Composable () -> Unit) {
    val colorMode = ColorMode.current
    Column(Modifier.fillMaxWidth().gap(1.cssRem)) {
        H2(
            Modifier
                .margin(top = 0.px, bottom = 0.cssRem)
                .fontSize(1.6.cssRem)
                .fontWeight(600)
                .color(colorMode.toSitePalette().brand.primary)
                .toAttrs()
        ) {
            Text(title)
        }
        content()
    }
}

@Composable
private fun SkillCategoryGrid(content: @Composable () -> Unit) {
    Div(
        Modifier
            .display(DisplayStyle.Flex)
            .flexWrap(FlexWrap.Wrap)
            .gap(1.cssRem)
            .fillMaxWidth()
            .toAttrs()
    ) {
        content()
    }
}

@Composable
private fun SkillCategoryCard(title: String, content: @Composable () -> Unit) {
    val colorMode = ColorMode.current
    Column(
        cardModifier(colorMode)
            .minWidth(10.cssRem)
            .flexGrow(1)
    ) {
        SpanText(
            title,
            Modifier
                .fontSize(0.95.cssRem)
                .fontWeight(700)
                .color(colorMode.toSitePalette().brand.primary)
        )
        content()
    }
}

@Composable
private fun StatImageCard(src: String, colorMode: ColorMode) {
    Div(
        cardModifier(colorMode)
            .flexGrow(1)
            .flexBasis(0.percent)
            .minWidth(18.cssRem)
            .display(DisplayStyle.Flex)
            .alignItems(AlignItems.Center)
            .justifyContent(JustifyContent.Center)
            .toAttrs()
    ) {
        Image(
            src = src,
            modifier = Modifier.fillMaxWidth().display(DisplayStyle.Block),
        )
    }
}

private fun statsTextColor(colorMode: ColorMode): String {
    return if (colorMode == ColorMode.DARK) "e3e1e9" else "1a1b21"
}
