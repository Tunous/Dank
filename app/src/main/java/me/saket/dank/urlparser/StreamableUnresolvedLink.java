package me.saket.dank.urlparser;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import me.saket.dank.di.DankApi;
import org.jetbrains.annotations.NotNull;

/**
 * Container for a streamable video's ID, which can only be used after its video URL has been
 * fetched using {@link DankApi#streamableVideoDetails(String)}.
 */
@AutoValue
public abstract class StreamableUnresolvedLink extends MediaLink implements Parcelable, UnresolvedMediaLink {

  @NotNull
  public abstract String unparsedUrl();

  public abstract String videoId();

  @Override
  public Link.Type type() {
    return Link.Type.SINGLE_VIDEO;
  }

  @Override
  public String highQualityUrl() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String lowQualityUrl() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String cacheKey() {
    return cacheKeyWithClassName(videoId());
  }

  public static StreamableUnresolvedLink create(String unparsedUrl, String videoId) {
    return new AutoValue_StreamableUnresolvedLink(unparsedUrl, videoId);
  }
}
