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
                android.R.attr.summaryOff, android.R.attr.disableDependentsState,
                getAttrId(context, "disableDependentsState"), getAttrId(context, "summaryOff"),
                getAttrId(context, "summaryOn")});
        STYLEABLES.put("DialogPreference", new int[] {android.R.attr.dialogTitle,
                android.R.attr.dialogMessage, android.R.attr.dialogIcon,
                android.R.attr.positiveButtonText, android.R.attr.negativeButtonText,
                android.R.attr.dialogLayout, getAttrId(context, "dialogIcon"),
                getAttrId(context, "dialogLayout"), getAttrId(context, "dialogMessage"),
                getAttrId(context, "dialogTitle"), getAttrId(context, "negativeButtonText"),
                getAttrId(context, "positiveButtonText")});
        STYLEABLES.put("EditTextPreference", new int[] {getAttrId(context, "useSimpleSummaryProvider")});
        STYLEABLES.put("PreferenceImageView", new int[] {android.R.attr.maxWidth,
                android.R.attr.maxHeight, getAttrId(context, "maxHeight"), getAttrId(context, "maxWidth")});
        STYLEABLES.put("ListPreference", new int[] {android.R.attr.entries, android.R.attr.entryValues,
                getAttrId(context, "entries"), getAttrId(context, "entryValues"),
                getAttrId(context, "useSimpleSummaryProvider")});
        STYLEABLES.put("Preference", new int[] {android.R.attr.icon, android.R.attr.persistent,
                android.R.attr.enabled, android.R.attr.layout,
                android.R.attr.title, android.R.attr.selectable, android.R.attr.key,
                android.R.attr.summary, android.R.attr.order, android.R.attr.widgetLayout,
                android.R.attr.dependency, android.R.attr.defaultValue,
                android.R.attr.shouldDisableView, android.R.attr.fragment,
                getAttrId(context, "allowDividerAbove"), getAttrId(context, "allowDividerBelow"),
                getAttrId(context, "defaultValue"), getAttrId(context, "dependency"),
                getAttrId(context, "enableCopying"), getAttrId(context, "enabled"),
                getAttrId(context, "fragment"), getAttrId(context, "icon"), getAttrId(context, "iconSpaceReserved"),
                getAttrId(context, "isPreferenceVisible"), getAttrId(context, "key"), getAttrId(context, "layout"),
                getAttrId(context, "order"), getAttrId(context, "persistent"), getAttrId(context, "selectable"),
                getAttrId(context, "shouldDisableView"), getAttrId(context, "singleLineTitle"),
                getAttrId(context, "summary"), getAttrId(context, "title"), getAttrId(context, "widgetLayout")});
        STYLEABLES.put("MultiSelectListPreference", new int[] {android.R.attr.entries,
                android.R.attr.entryValues, getAttrId(context, "entries"), getAttrId(context, "entryValues")});
        STYLEABLES.put("PreferenceFragment", new int[] {android.R.attr.layout, android.R.attr.divider,
                android.R.attr.dividerHeight, getAttrId(context, "allowDividerAfterLastItem")});
        STYLEABLES.put("PreferenceFragmentCompat", new int[] {android.R.attr.layout, android.R.attr.divider,
                android.R.attr.dividerHeight, getAttrId(context, "allowDividerAfterLastItem")});
        STYLEABLES.put("PreferenceGroup", new int[] {android.R.attr.orderingFromXml,
                getAttrId(context, "initialExpandedChildrenCount"), getAttrId(context, "orderingFromXml")});
        STYLEABLES.put("BackgroundStyle", new int[] {android.R.attr.selectableItemBackground,
                getAttrId(context, "selectableItemBackground")});
        STYLEABLES.put("SeekBarPreference", new int[] {android.R.attr.layout, android.R.attr.max,
                getAttrId(context, "adjustable"), getAttrId(context, "min"),
                getAttrId(context, "seekBarIncrement"), getAttrId(context, "seekBarValueSuffix"),
                getAttrId(context, "showSeekBarValue"), getAttrId(context, "updatesContinuously")});
        STYLEABLES.put("SwitchPreferenceX", new int[] {android.R.attr.summaryOn, android.R.attr.summaryOff,
                android.R.attr.disableDependentsState, android.R.attr.switchTextOn,
                android.R.attr.switchTextOff, getAttrId(context, "disableDependentsState"),
                getAttrId(context, "summaryOff"), getAttrId(context, "summaryOn"), getAttrId(context, "switchTextOff"),
                getAttrId(context, "switchTextOn")});
        STYLEABLES.put("SwitchPreferenceCompatX", new int[] {android.R.attr.summaryOn,
                android.R.attr.summaryOff, android.R.attr.disableDependentsState,  android.R.attr.switchTextOn,
                android.R.attr.switchTextOff, getAttrId(context, "disableDependentsState"),
                getAttrId(context, "summaryOff"), getAttrId(context, "summaryOn"),
                getAttrId(context, "switchTextOff"), getAttrId(context, "switchTextOn")
        });
    }

    public static int[] getStyleableArr(Context context, String name) {
        if (TextUtils.isEmpty(name)) {
            return new int[] {};
        }

        return getStyleables(context).get(name);
    }

    public static Integer getAttrId(Context context, String name) {
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "attr", context.getPackageName());
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

    public static Integer getDrawableId(Context context, String name) {
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "drawable", context.getPackageName());
    }
}
