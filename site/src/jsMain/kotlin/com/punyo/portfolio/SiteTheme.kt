package com.punyo.portfolio

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color

private object ColorScheme {
    val primaryLight = Color.rgb(0x4C5C92)
    val secondaryLight = Color.rgb(0x3D5F90)
    val backgroundLight = Color.rgb(0xFAF8FF)
    val onBackgroundLight = Color.rgb(0x1A1B21)
    val outlineVariantLight = Color.rgb(0xC6C6D0)
    val surfaceContainerLowLight = Color.rgb(0xF4F3FA)

    val primaryDark = Color.rgb(0xB5C4FF)
    val secondaryDark = Color.rgb(0xA6C8FF)
    val backgroundDark = Color.rgb(0x121318)
    val onBackgroundDark = Color.rgb(0xE3E1E9)
    val outlineVariantDark = Color.rgb(0x45464F)
    val surfaceContainerLowDark = Color.rgb(0x1A1B21)
}

/**
 * @property nearBackground A useful color to apply to a container that should differentiate itself from the background
 *   but just a little.
 */
class SitePalette(
    val nearBackground: Color,
    val cobweb: Color,
    val brand: Brand,
) {
    class Brand(
        val primary: Color = ColorScheme.primaryLight,
        val accent: Color = ColorScheme.secondaryLight,
    )
}

object SitePalettes {
    val light = SitePalette(
        nearBackground = ColorScheme.surfaceContainerLowLight,
        cobweb = ColorScheme.outlineVariantLight,
        brand = SitePalette.Brand(
            primary = ColorScheme.primaryLight,
            accent = ColorScheme.secondaryLight,
        )
    )
    val dark = SitePalette(
        nearBackground = ColorScheme.surfaceContainerLowDark,
        cobweb = ColorScheme.outlineVariantDark,
        brand = SitePalette.Brand(
            primary = ColorScheme.primaryDark,
            accent = ColorScheme.secondaryDark,
        )
    )
}

fun ColorMode.toSitePalette(): SitePalette {
    return when (this) {
        ColorMode.LIGHT -> SitePalettes.light
        ColorMode.DARK -> SitePalettes.dark
    }
}

@InitSilk
fun initTheme(ctx: InitSilkContext) {
    ctx.theme.palettes.light.background = ColorScheme.backgroundLight
    ctx.theme.palettes.light.color = ColorScheme.onBackgroundLight
    ctx.theme.palettes.dark.background = ColorScheme.backgroundDark
    ctx.theme.palettes.dark.color = ColorScheme.onBackgroundDark
}
