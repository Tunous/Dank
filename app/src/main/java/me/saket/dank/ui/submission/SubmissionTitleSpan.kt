package me.saket.dank.ui.submission

import android.text.Spannable
import me.saket.dank.widgets.SelectionLimitingTextView

// This is a no-op span to identify title position in TextView
object SubmissionTitleSpan {
  @JvmStatic fun limitSelectionForTextView(view: SelectionLimitingTextView) {
    val title = view.text as Spannable
    title.getSpans(0, title.length, SubmissionTitleSpan::class.java).firstOrNull()?.let {
      view.setSelectionLimits(title.getSpanStart(it), title.getSpanEnd(it))
    }
  }
}
