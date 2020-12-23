package tv.twitch.android.mod.models.preferences;


import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.SOURCE)
@StringDef({EmoteSize.SMALL, EmoteSize.MEDIUM, EmoteSize.LARGE})
public @interface EmoteSize {
    String SMALL = "small";
    String MEDIUM = "medium";
    String LARGE = "large";
}
