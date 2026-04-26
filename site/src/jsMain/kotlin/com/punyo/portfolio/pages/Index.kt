package com.punyo.portfolio.pages

import androidx.compose.runtime.Composable
import com.punyo.portfolio.components.layouts.PageLayoutData
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
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
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
import kotlin.js.Date

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

@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Home"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun HomePage() {
    Column(Modifier.fillMaxWidth().gap(3.cssRem)) {
        BioSection()
        AboutSection()
        CertificationSection()
        LanguageStatsSection()
    }
}

@Composable
private fun BioSection() {
    val colorMode = ColorMode.current
    Row(
        HeroSectionStyle.toModifier(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            src = "/avatar.png",
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
                Text("Punyo")
            }
            P(
                Modifier
                    .margin(0.px)
                    .fontSize(1.05.cssRem)
                    .color(colorMode.toSitePalette().brand.primary)
                    .toAttrs()
            ) {
                Text("Department of Computer Science, Nagoya Institute of Technology")
            }
            P(
                Modifier
                    .margin(0.px)
                    .fontSize(0.95.cssRem)
                    .color(colorMode.toPalette().color.toRgb().copyf(alpha = 0.75f))
                    .toAttrs()
            ) {
                val currentYear = Date().getFullYear()
                Text("キーボードをゆるゆるしばきつづけて早${currentYear - 2017 - 1}年 機械いじりとおさんぽとずんだもんがすき")
            }

            Row(Modifier.gap(0.5.cssRem).flexWrap(FlexWrap.Wrap), verticalAlignment = Alignment.CenterVertically) {
                Link(path = "https://wakatime.com/@Punyo") {
                    Image(
                        src = "https://wakatime.com/badge/user/0bcd2543-94ea-40a0-a737-bb2f87523850.svg",
                        modifier = linkTextModifier(colorMode).display(DisplayStyle.Block)
                    )
                }
                ExternalLink(
                    href = "https://github.com/Punyo",
                    modifier = linkTextModifier(colorMode)
                ) { Text("GitHub") }
                ExternalLink(
                    href = "https://qiita.com/Punyo",
                    modifier = linkTextModifier(colorMode)
                ) { Text("Qiita") }
            }
        }
    }
}

@Composable
private fun AboutSection() {
    val colorMode = ColorMode.current
    Column(Modifier.fillMaxWidth().gap(1.cssRem)) {
        H2(
            Modifier
                .margin(top = 0.px, bottom = 0.cssRem)
                .fontSize(1.6.cssRem)
                .fontWeight(600)
                .color(colorMode.toSitePalette().brand.primary)
                .toAttrs()
        ) { Text("技術") }
        Div(
            Modifier
                .display(DisplayStyle.Flex)
                .flexWrap(FlexWrap.Wrap)
                .gap(1.cssRem)
                .fillMaxWidth()
                .toAttrs()
        ) {
            SkillCategoryCard(title = "言語/フレームワーク/プラットフォーム") {
                Image(
                    src = "https://go-skill-icons.vercel.app/api/icons?i=android,c,cpp,cs,dotnet,unity,python,java,jetpackcompose,kotlin,ktor&titles=true",
                    modifier = Modifier.fillMaxWidth().display(DisplayStyle.Block),
                )
            }
        }
        Div(
            Modifier
                .display(DisplayStyle.Flex)
                .flexWrap(FlexWrap.Wrap)
                .gap(1.cssRem)
                .fillMaxWidth()
                .toAttrs()
        ) {
            SkillCategoryCard(title = "ツール") {
                Image(
                    src = "https://go-skill-icons.vercel.app/api/icons?i=androidstudio,azure,docker,git,github,githubactions,githubpages,googleplayconsole,idea,visualstudio,vscode&titles=true",
                    modifier = Modifier.fillMaxWidth().display(DisplayStyle.Block),
                )
            }
        }
    }
}

@Composable
private fun LanguageStatsSection() {
    val colorMode = ColorMode.current
    Column(Modifier.fillMaxWidth().gap(1.cssRem)) {
        H2(
            Modifier
                .margin(top = 0.px, bottom = 0.cssRem)
                .fontSize(1.6.cssRem)
                .fontWeight(600)
                .color(colorMode.toSitePalette().brand.primary)
                .toAttrs()
        ) { Text("使用言語統計") }
        Div(
            Modifier
                .display(DisplayStyle.Flex)
                .flexWrap(FlexWrap.Wrap)
                .alignItems(AlignItems.Stretch)
                .gap(1.cssRem)
                .fillMaxWidth()
                .toAttrs()
        ) {
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
                    src = "https://github-readme-stats-owokza06v-punyos-projects.vercel.app/api/top-langs?username=Punyo&hide_title=false&hide_border=true&bg_color=00000000&text_color=${if (colorMode == ColorMode.DARK) "e3e1e9" else "1a1b21"}&locale=ja&langs_count=10&exclude_repo=patched_pyrroline",
                    modifier = Modifier.fillMaxWidth().display(DisplayStyle.Block),
                )
            }
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
                    src = "https://github-readme-stats-owokza06v-punyos-projects.vercel.app/api/wakatime?username=Punyo&layout=compact&hide_title=false&hide_border=true&bg_color=00000000&text_color=${if (colorMode == ColorMode.DARK) "e3e1e9" else "1a1b21"}&locale=ja",
                    modifier = Modifier.fillMaxWidth().display(DisplayStyle.Block),
                )
            }
        }
    }
}

@Composable
private fun CertificationSection() {
    val colorMode = ColorMode.current
    Column(Modifier.fillMaxWidth().gap(1.cssRem)) {
        H2(
            Modifier
                .margin(top = 0.px, bottom = 0.cssRem)
                .fontSize(1.6.cssRem)
                .fontWeight(600)
                .color(colorMode.toSitePalette().brand.primary)
                .toAttrs()
        ) { Text("保有資格") }
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
                    listOf(
                        "応用情報技術者試験" to "2025/12",
                        "TOEIC Listening & Reading Test (Score:945)" to "2024/04",
                        "実用英語技能検定 準1級" to "2022/03",
                    ).forEach { (name, date) ->
                        Tr {
                            Td(Modifier.padding(0.4.cssRem).toAttrs()) { Text(name) }
                            Td(Modifier.padding(0.4.cssRem).toAttrs()) { Text(date) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SkillCategoryCard(title: String, content: @Composable () -> Unit) {
    Column(
        cardModifier(ColorMode.current)
            .minWidth(10.cssRem)
            .flexGrow(1)
    ) {
        SpanText(
            title,
            Modifier
                .fontSize(0.95.cssRem)
                .fontWeight(700)
                .color(ColorMode.current.toSitePalette().brand.primary)
        )
        content()
//        Ul(Modifier.margin(top = 0.3.cssRem, bottom = 0.px).padding(left = 1.2.cssRem).toAttrs()) {
//            skills.forEach { skill ->
//                Li { Text(skill) }
//            }
//        }
    }
}
