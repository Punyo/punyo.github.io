package com.punyo.portfolio.pages

import androidx.compose.runtime.Composable
import com.punyo.portfolio.components.layouts.PageLayoutData
import com.punyo.portfolio.components.sections.BioSection
import com.punyo.portfolio.components.sections.CertificationSection
import com.punyo.portfolio.components.sections.HomeCertification
import com.punyo.portfolio.components.sections.HomeLanguageStatImage
import com.punyo.portfolio.components.sections.HomeSkillCategory
import com.punyo.portfolio.components.sections.HomeSocialLink
import com.punyo.portfolio.components.sections.LanguageStatsSection
import com.punyo.portfolio.components.sections.SkillsSection
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.css.cssRem

@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Home"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun HomePage() {
    Column(Modifier.fillMaxWidth().gap(3.cssRem)) {
        BioSection(
            name = "Punyo",
            affiliation = "Department of Computer Science, Nagoya Institute of Technology",
            message = "日々キーボードをしばいている名古屋の学生 機械いじりとおさんぽとずんだもんがすきです",
            avatarSrc = "/avatar.png",
            wakaTimeProfileUrl = "https://wakatime.com/@Punyo",
            wakaTimeBadgeSrc = "https://wakatime.com/badge/user/0bcd2543-94ea-40a0-a737-bb2f87523850.svg",
            socialLinks = listOf(
                HomeSocialLink(label = "GitHub", href = "https://github.com/Punyo"),
                HomeSocialLink(label = "Qiita", href = "https://qiita.com/Punyo"),
            ),
        )
        SkillsSection(
            categories = listOf(
                HomeSkillCategory(
                    title = "言語/フレームワーク/プラットフォーム",
                    iconsSrc = "https://go-skill-icons.vercel.app/api/icons?i=android,c,cpp,cs,dotnet,unity,python,java,jetpackcompose,kotlin,ktor&titles=true",
                ),
                HomeSkillCategory(
                    title = "ツール",
                    iconsSrc = "https://go-skill-icons.vercel.app/api/icons?i=androidstudio,azure,docker,git,github,githubactions,githubpages,googleplayconsole,idea,visualstudio,vscode&titles=true",
                )
            )
        )
        CertificationSection(
            certifications = listOf(
                HomeCertification(name = "応用情報技術者試験", date = "2025/12"),
                HomeCertification(name = "TOEIC Listening & Reading Test (Score:945)", date = "2024/04"),
                HomeCertification(name = "実用英語技能検定 準1級", date = "2022/03"),
            )
        )
        LanguageStatsSection(
            statImages = listOf(
                HomeLanguageStatImage(
                    srcTemplate = "https://github-readme-stats-owokza06v-punyos-projects.vercel.app/api/top-langs?username=Punyo&hide_title=false&hide_border=true&bg_color=00000000&text_color={textColor}&locale=ja&langs_count=10&exclude_repo=patched_pyrroline",
                ),
                HomeLanguageStatImage(
                    srcTemplate = "https://github-readme-stats-owokza06v-punyos-projects.vercel.app/api/wakatime?username=Punyo&layout=compact&hide_title=false&hide_border=true&bg_color=00000000&text_color={textColor}&locale=ja",
                ),
            )
        )
    }
}
