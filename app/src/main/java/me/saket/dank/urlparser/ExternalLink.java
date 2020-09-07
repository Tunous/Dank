package me.saket.dank.urlparser;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import org.jetbrains.annotations.NotNull;

/**
 * Link that can only be opened in a browser.
 */
@AutoValue
public abstract class ExternalLink extends Link implements Parcelable {

  @NotNull
  @Override
  public abstract String unparsedUrl();

  @Override
  public Type type() {
    return Type.EXTERNAL;
  }

  public static ExternalLink create(String unparsedUrl) {
    return new AutoValue_ExternalLink(unparsedUrl);
  }
}
