package tv.twitch.android.mod.models.preferences;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.SOURCE)
@StringDef({UserMessagesFiltering.DISABLED, UserMessagesFiltering.SUBS, UserMessagesFiltering.MODS, UserMessagesFiltering.BROADCASTER})
public @interface UserMessagesFiltering {
    String DISABLED = "disabled";
    String SUBS = "subs";
    String MODS = "mods";
    String BROADCASTER = "broadcaster";
}
