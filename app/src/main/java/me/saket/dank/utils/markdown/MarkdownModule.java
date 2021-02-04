package me.saket.dank.utils.markdown;

import android.app.Application;

import androidx.core.content.ContextCompat;

import com.nytimes.android.external.cache3.Cache;
import com.nytimes.android.external.cache3.CacheBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.noties.markwon.Markwon;
import io.noties.markwon.core.MarkwonTheme;
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin;
import io.noties.markwon.ext.tables.TablePlugin;
import io.noties.markwon.ext.tables.TableTheme;
import io.noties.markwon.ext.tasklist.TaskListPlugin;
import me.saket.dank.R;
import me.saket.dank.markdownhints.MarkdownHintOptions;
import me.saket.dank.markdownhints.MarkdownSpanPool;
import me.saket.dank.utils.SafeFunction;
import me.saket.dank.utils.markdown.markwon.MarkwonBasedMarkdownRenderer;
import me.saket.dank.utils.markdown.markwon.MarkwonThemePlugin;

@Module
public class MarkdownModule {

  @Provides
  Markdown markdown(MarkwonBasedMarkdownRenderer renderer) {
    return renderer;
  }

  @Provides
  Markwon markwon(
      Application appContext,
      MarkwonThemePlugin themePlugin,
      StrikethroughPlugin strikethroughPlugin,
      TablePlugin tablePlugin,
      TaskListPlugin taskListPlugin
  ) {
    return Markwon.builder(appContext)
        .usePlugin(strikethroughPlugin)
        .usePlugin(tablePlugin)
        .usePlugin(taskListPlugin)
        .usePlugin(themePlugin)
        .build();
  }

  @Provides
  static MarkdownHintOptions provideMarkdownHintOptions(Application appContext) {
    SafeFunction<Integer, Integer> colors = resId -> ContextCompat.getColor(appContext, resId);
    SafeFunction<Integer, Integer> dimens = resId -> appContext.getResources().getDimensionPixelSize(resId);

    return MarkdownHintOptions.builder()
        .syntaxColor(colors.apply(R.color.markdown_syntax))

        .blockQuoteIndentationRuleColor(colors.apply(R.color.markdown_blockquote_indentation_rule))
        .blockQuoteTextColor(colors.apply(R.color.markdown_blockquote_text))
        .blockQuoteVerticalRuleStrokeWidth(dimens.apply(R.dimen.markdown_blockquote_vertical_rule_stroke_width))

        .linkUrlColor(colors.apply(R.color.markdown_link_url))
        .linkTextColor(colors.apply(R.color.markdown_link_text))
        .spoilerSyntaxHintColor(colors.apply(R.color.markdown_spoiler_syntax_hint_for_editor))
        .spoilerHiddenContentOverlayColor(colors.apply(R.color.markdown_spoiler_hidden_content_overlay))

        .listBlockIndentationMargin(dimens.apply(R.dimen.markdown_text_block_indentation_margin))

        .horizontalRuleColor(colors.apply(R.color.markdown_horizontal_rule))
        .horizontalRuleStrokeWidth(dimens.apply(R.dimen.markdown_horizontal_rule_stroke_width))

        .inlineCodeBackgroundColor(colors.apply(R.color.markdown_inline_code_background))

        .tableBorderColor(colors.apply(R.color.markdown_table_border))

        .build();
  }

  @Provides
  @Singleton
  @Named("markwon_spans_renderer")
  static Cache<String, CharSequence> markdownCache() {
    return CacheBuilder.newBuilder()
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build();
  }

  @Provides
  static MarkdownSpanPool markdownSpanPool(MarkwonTheme theme) {
    return new MarkdownSpanPool(theme);
  }

  @Provides
  static TableTheme markdownTableTheme(Application appContext, MarkdownHintOptions options) {
    return TableTheme.buildWithDefaults(appContext)
        .tableBorderColor(options.tableBorderColor())
        .build();
  }

  @Provides
  static TablePlugin markdownTablePlugin(TableTheme theme) {
    return TablePlugin.create(theme);
  }

  @Provides
  static TaskListPlugin taskListPlugin(Application appContext) {
    return TaskListPlugin.create(appContext);
  }

  @Provides
  static StrikethroughPlugin strikethroughPlugin() {
    return StrikethroughPlugin.create();
  }
}
