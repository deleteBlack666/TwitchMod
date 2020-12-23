package tv.twitch.android.mod.models.preferences;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({SureStreamAdBlock.DISABLED, SureStreamAdBlock.V1, SureStreamAdBlock.V2})
public @interface SureStreamAdBlock {
    String DISABLED = "disabled";
    String V1 = "v1";
    String V2 = "v2";
}
