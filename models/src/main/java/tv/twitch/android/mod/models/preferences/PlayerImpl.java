package tv.twitch.android.mod.models.preferences;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.SOURCE)
@StringDef({PlayerImpl.AUTO, PlayerImpl.CORE, PlayerImpl.EXO})
public @interface PlayerImpl {
    String AUTO = "auto";
    String CORE = "playercore";
    String EXO = "exoplayer_2";
}
