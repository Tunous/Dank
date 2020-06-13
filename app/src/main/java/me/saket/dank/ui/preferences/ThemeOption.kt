package me.saket.dank.ui.preferences

import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDelegate
import me.saket.dank.R

enum class ThemeOption(
  @StringRes val title: Int,
  @StyleRes val theme: Int,
  val mode: Int
) {
  LIGHT(
    R.string.userprefs_theme_light,
    R.style.DankTheme,
    AppCompatDelegate.MODE_NIGHT_NO
  ),

  DARK(
    R.string.userprefs_theme_dark,
    R.style.DankTheme,
    AppCompatDelegate.MODE_NIGHT_YES
  ),

  AUTO(
    R.string.userprefs_theme_battery,
    R.style.DankTheme,
    AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
  ),

  SYSTEM(
    R.string.userprefs_theme_system,
    R.style.DankTheme,
    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
  )
}
