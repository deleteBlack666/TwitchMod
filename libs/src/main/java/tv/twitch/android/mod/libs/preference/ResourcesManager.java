package tv.twitch.android.mod.libs.preference;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

public class ResourcesManager {
    private static final HashMap<String, int[]> STYLEABLES = new HashMap<>();

    public static HashMap<String, int[]> getStyleables(Context context) {
        if (STYLEABLES.isEmpty()) {
            synchronized (ResourcesManager.class) {
                if (STYLEABLES.isEmpty()) {
                    fillStyleables(context);
                }
            }
        }

        return STYLEABLES;
    }

    private static void fillStyleables(Context context) {
        STYLEABLES.put("CheckBoxPreference", new int[] {android.R.attr.summaryOn,
                android.R.attr.summaryOff, android.R.attr.disableDependentsState});
        STYLEABLES.put("DialogPreference", new int[] {android.R.attr.dialogTitle,
                android.R.attr.dialogMessage, android.R.attr.dialogIcon,
                android.R.attr.positiveButtonText, android.R.attr.negativeButtonText,
                android.R.attr.dialogLayout});
        STYLEABLES.put("EditTextPreference", new int[] {getAttrId(context, "useSimpleSummaryProvider")});
        STYLEABLES.put("PreferenceImageView", new int[] {android.R.attr.maxWidth,
                android.R.attr.maxHeight});
        STYLEABLES.put("ListPreference", new int[] {android.R.attr.entries, android.R.attr.entryValues,
                getAttrId(context, "useSimpleSummaryProvider")});
        STYLEABLES.put("Preference", new int[] {android.R.attr.icon, android.R.attr.persistent,
                android.R.attr.enabled, android.R.attr.layout,
                android.R.attr.title, android.R.attr.selectable, android.R.attr.key,
                android.R.attr.summary, android.R.attr.order, android.R.attr.widgetLayout,
                android.R.attr.dependency, android.R.attr.defaultValue,
                android.R.attr.shouldDisableView, android.R.attr.fragment,
                getAttrId(context, "allowDividerAbove"), getAttrId(context, "allowDividerBelow"),
                getAttrId(context, "enableCopying"), getAttrId(context, "iconSpaceReserved"),
                getAttrId(context, "isPreferenceVisible"), getAttrId(context, "singleLineTitle"),
                getAttrId(context, "xPreference")});
        STYLEABLES.put("MultiSelectListPreference", new int[] {android.R.attr.entries, android.R.attr.entryValues});
        STYLEABLES.put("PreferenceFragment", new int[] {android.R.attr.layout, android.R.attr.divider,
                android.R.attr.dividerHeight, getAttrId(context, "allowDividerAfterLastItem")});
        STYLEABLES.put("PreferenceFragmentCompat", new int[] {android.R.attr.layout, android.R.attr.divider,
                android.R.attr.dividerHeight, getAttrId(context, "allowDividerAfterLastItem")});
        STYLEABLES.put("PreferenceGroup", new int[] {android.R.attr.orderingFromXml,
                getAttrId(context, "initialExpandedChildrenCount")});
        STYLEABLES.put("BackgroundStyle", new int[] {android.R.attr.selectableItemBackground});
        STYLEABLES.put("SeekBarPreference", new int[] {android.R.attr.layout, android.R.attr.max,
                getAttrId(context, "adjustable"), getAttrId(context, "min"),
                getAttrId(context, "seekBarIncrement"), getAttrId(context, "seekBarValueSuffix"),
                getAttrId(context, "showSeekBarValue"), getAttrId(context, "updatesContinuously")});
        STYLEABLES.put("SwitchPreference", new int[] {android.R.attr.summaryOn, android.R.attr.summaryOff,
                android.R.attr.disableDependentsState, android.R.attr.switchTextOn,
                android.R.attr.switchTextOff});
        STYLEABLES.put("SwitchPreferenceCompat", new int[] {android.R.attr.summaryOn,
                android.R.attr.summaryOff, android.R.attr.disableDependentsState,  android.R.attr.switchTextOn,
                android.R.attr.switchTextOff});
    }

    public static int[] getStyleableArr(Context context, String name) {
        if (TextUtils.isEmpty(name)) {
            return new int[] {};
        }

        return getStyleables(context).get(name);
    }

    public static Integer getAttrId(Context context, String name) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier(name, "attr", context.getPackageName());
        if (id == 0) {
            Log.w("RM", "attr " + name + " not found!");
        }

        return id;
    }

    public static Integer getId(Context context, String name) {
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "id", context.getPackageName());
    }

    public static Integer getLayoutId(Context context, String name) {
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "layout", context.getPackageName());
    }

    public static Integer getStyleId(Context context, String name) {
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "style", context.getPackageName());
    }

    public static Integer getColorId(Context context, String name) {
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "color", context.getPackageName());
    }

    public static Integer getStringId(Context context, String name) {
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "string", context.getPackageName());
    }

    public static String getString(Context context, String name, Object... formatArgs) {
        int id = ResourcesManager.getStringId(context, name);
        if (id == 0) {
            return "STRING RESOURCE NOT FOUND: '" + name + "'";
        }
        return context.getResources().getString(id, formatArgs);
    }

    public static String getString(Context context, String name) {
        int resId = getStringId(context, name);
        if (resId == 0) {
            return "STRING RESOURCE NOT FOUND: '" + name + "'";
        } else {
            return getStringById(context, resId);
        }
    }

    public static String getStringById(Context context, Integer id) {
        return context.getResources().getString(id);
    }

    public static Integer getDrawableId(Context context, String name) {
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "drawable", context.getPackageName());
    }
}
