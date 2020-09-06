package me.saket.dank.urlparser;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import org.jetbrains.annotations.NotNull;

/**
 * Never used alone. Always with {@link RedditSubmissionLink}.
 */
@AutoValue
public abstract class RedditCommentLink extends RedditLink implements Parcelable {

  @NotNull
  @Override
  public abstract String unparsedUrl();

  public abstract String id();

  /**
   * Number of parent comments to show.
   */
  public abstract int contextCount();

  @Override
  public RedditLinkType redditLinkType() {
    return RedditLinkType.COMMENT;
  }

  public static RedditCommentLink create(String unparsedUrl, String id, Integer contextCount) {
    return new AutoValue_RedditCommentLink(unparsedUrl, id, contextCount);
  }
}
