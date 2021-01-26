package me.saket.dank.ui.media.gfycat;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonClass;
import com.squareup.moshi.Moshi;

@JsonClass(generateAdapter = true, generator = "avm")
@AutoValue
public abstract class GfycatOauthResponse {

  @Json(name = "expires_in")
  public abstract long expiresInMillis();

  @Json(name = "access_token")
  public abstract String accessToken();

  public static JsonAdapter<GfycatOauthResponse> jsonAdapter(Moshi moshi) {
    return new AutoValue_GfycatOauthResponse.MoshiJsonAdapter(moshi);
  }

  public static GfycatOauthResponse create(long expiresInMillis, String accessToken) {
    return new AutoValue_GfycatOauthResponse(expiresInMillis, accessToken);
  }
}
