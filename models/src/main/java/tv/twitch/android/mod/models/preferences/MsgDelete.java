package tv.twitch.android.mod.models.preferences;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.SOURCE)
@StringDef({MsgDelete.DEFAULT, MsgDelete.MOD, MsgDelete.STRIKETHROUGH})
public @interface MsgDelete {
    String DEFAULT = "default";
    String MOD = "mod";
    String STRIKETHROUGH = "strikethrough";
}
