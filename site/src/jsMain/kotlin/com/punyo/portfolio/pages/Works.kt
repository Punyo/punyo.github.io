package com.punyo.portfolio.pages

import androidx.compose.runtime.Composable
import com.punyo.portfolio.components.layouts.PageLayoutData
import com.punyo.portfolio.components.sections.WorkLink
import com.punyo.portfolio.components.sections.WorkMedia
import com.punyo.portfolio.components.sections.WorkProject
import com.punyo.portfolio.components.sections.WorkProjectCard
import com.punyo.portfolio.components.sections.WorkYearSection
import com.punyo.portfolio.components.ui.pageTitleModifier
import com.punyo.portfolio.toSitePalette
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.TagElement
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLElement

val worksData: List<WorkYearSection> = listOf(
    WorkYearSection(
        "2024", listOf(
            WorkProject(
                name = "TaskMate（第2回 C0deハッカソン with pixiv にて制作）",
                media = listOf(
                    WorkMedia.Video("/works/taskmate/assignmentscreen.mp4"),
                    WorkMedia.Image("/works/taskmate/settings.png"),
                ),
                description = {
                    P {
                        Text("Moodleサーバーから情報を取得し、未提出/提出済みの課題とその期限をすぐに確認することができるAndroidアプリです。また、ユーザーが指定した時間までに課題を提出していなかった場合、指定されたアプリをロックする機能も実装されています。")
                    }
                    P {
                        Text("ハッカソンではバックエンド1人とアプリ側2人で分担して制作を行い、私はアプリ側のデータレイヤすべてと設定画面のUI（写真右）を担当しました。")
                    }
                },
                techStack = listOf("Jetpack Compose", "Ktor", "Node.js（バックエンド）"),
                links = listOf(
                    WorkLink("Qiitaでの紹介記事", "https://qiita.com/Punyo/items/15a8d9fb42ec37f57280"),
                ),
            ),
            WorkProject(
                name = "NITechSearch：名工大の講義室使用状況検索アプリ",
                media = listOf(
                    WorkMedia.Video("/works/nitechvacancyviewer/video.mp4"),
                ),
                description = {
                    P {
                        Text("事前に数日分の講義室の予約状況をスクレイピングにより大学のサーバーから取得して端末内に保存し、現在空室になっている講義室と建物別の空室の数をすぐに確認できるAndroidアプリです。また、今日の講義室の予約状況と使用時間を部屋別に確認することもできます。")
                    }
                    P {
                        Text("2024年10月から学内向けにGoogle Playにて配信を開始しました。")
                    }
                },
                techStack = listOf("Jetpack Compose", "Retrofit", "Jsoup", "Tink"),
                links = listOf(
                    WorkLink("詳細ページ", "/works/nitechvacancyviewer", isExternal = false),
                    WorkLink("GitHub", "https://github.com/Punyo/NITechVacancyViewer"),
                    WorkLink(
                        "Google Play",
                        "https://play.google.com/store/apps/details?id=com.punyo.nitechvacancyviewer"
                    ),
                ),
            ),
        )
    ),
    WorkYearSection(
        "2021", listOf(
            WorkProject(
                name = "単語帳アプリ",
                media = listOf(
                    WorkMedia.Image("/works/wordlist/genrelist.png"),
                    WorkMedia.Image("/works/wordlist/test.png"),
                    WorkMedia.Image("/works/wordlist/quiz.png"),
                    WorkMedia.Image("/works/wordlist/stat.png"),
                ),
                description = {
                    P {
                        Text("項目ごとに単語を登録でき、それらをランダムで出題する機能を有する単語帳アプリです。")
                    }
                    P {
                        Text("単語ごとの正解率を管理する機能も実装されています。")
                    }
                },
                techStack = listOf("Xamarin.Android"),
                links = listOf(
                    WorkLink("GitHub", "https://github.com/Punyo/AndroidApp"),
                ),
            ),
        )
    ),
    WorkYearSection(
        "2019", listOf(
            WorkProject(
                name = "時間割＆ToDo - 時間割、ToDoリスト",
                media = listOf(
                    WorkMedia.Image("/works/jikanwariandtodo/screenshot1.png"),
                    WorkMedia.Image("/works/jikanwariandtodo/screenshot2.png"),
                    WorkMedia.Image("/works/jikanwariandtodo/screenshot3.png"),
                    WorkMedia.Image("/works/jikanwariandtodo/screenshot4.png"),
                ),
                description = {
                    P {
                        Text("時間割とToDoリストを管理するためのAndroidアプリです。")
                    }
                    P {
                        Text("時間割機能では、どの曜日に授業があり、何時限あるのかを設定でき、最大10時限まで対応しています。授業内容はあらかじめ30種類が登録されており、さらに追加することも可能です。また、作成した時間割を二次元コードとして出力する機能も実装されています。")
                    }
                    P {
                        Text("ToDo機能では、1か月あたり最大20件のToDoを登録でき、その進捗状況を管理することができます。登録したToDoリストは3か月間保存され、完了したかどうかを追跡することができます。")
                    }
                    P {
                        TagElement<HTMLElement>("s", {}) { Text("2019年3月からGoogle Playにて配信を開始しました。") }
                        Text(" 現在は配信を停止しています")
                    }
                },
                techStack = listOf("Unity"),
                links = listOf(
                    WorkLink("Google Play 配信停止済み", "", isExternal = false),
                ),
            ),
        )
    ),
)

@InitRoute
fun initWorksPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Works"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun WorksPage() {
    Column(
        Modifier
            .fillMaxWidth()
            .attr("id", "works")
            .attr("aria-label", "Works")
    ) {
        H1(pageTitleModifier().toAttrs()) {
            Text("Works")
        }
        Column(Modifier.fillMaxWidth().gap(0.cssRem)) {
            worksData.forEach { section ->
                H2(
                    Modifier
                        .margin(top = 1.5.cssRem, bottom = 0.25.cssRem)
                        .fontSize(1.8.cssRem)
                        .fontWeight(600)
                        .color(ColorMode.current.toSitePalette().brand.primary)
                        .toAttrs()
                ) {
                    Text(section.year)
                }
                section.projects.forEach { project ->
                    WorkProjectCard(project)
                }
            }
        }

    }
}
