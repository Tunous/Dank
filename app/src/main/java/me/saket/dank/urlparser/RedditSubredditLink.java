package me.saket.dank.urlparser;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import org.jetbrains.annotations.NotNull;

@AutoValue
public abstract class RedditSubredditLink extends RedditLink implements Parcelable {

  @NotNull
  @Override
  public abstract String unparsedUrl();

  public abstract String name();

  @Override
  public RedditLinkType redditLinkType() {
    return RedditLinkType.SUBREDDIT;
  }

  public static RedditSubredditLink create(String unparsedUrl, String subredditName) {
    return new AutoValue_RedditSubredditLink(unparsedUrl, subredditName);
  }

  public static RedditSubredditLink create(String subredditName) {
    return new AutoValue_RedditSubredditLink("https://reddit.com/r/" + subredditName, subredditName);
  }
}
