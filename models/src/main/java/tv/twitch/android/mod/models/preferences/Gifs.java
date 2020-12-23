package tv.twitch.android.mod.models.preferences;


import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.SOURCE)
@StringDef({Gifs.DISABLED, Gifs.STATIC, Gifs.ANIMATED})
public @interface Gifs {
    String DISABLED = "disabled";
    String STATIC = "static";
    String ANIMATED = "animated";
}
