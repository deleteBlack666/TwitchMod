package tv.twitch.android.mod.models.preferences;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({SureStreamAdBlock.DISABLED, SureStreamAdBlock.V1, SureStreamAdBlock.V3})
public @interface SureStreamAdBlock {
    String DISABLED = "disabled";
    String V1 = "v1";
    String V3 = "v3";
}
