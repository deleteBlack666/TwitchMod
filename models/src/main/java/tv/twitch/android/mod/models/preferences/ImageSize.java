package tv.twitch.android.mod.models.preferences;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.SOURCE)
@StringDef({ImageSize.SMALL, ImageSize.MEDIUM, ImageSize.LARGE})
public @interface ImageSize {
    String SMALL = "small";
    String MEDIUM = "medium";
    String LARGE = "large";
}
