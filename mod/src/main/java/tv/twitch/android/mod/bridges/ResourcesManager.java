package tv.twitch.android.mod.bridges;


import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.LruCache;

import java.util.HashMap;

import tv.twitch.android.mod.utils.Logger;


public class ResourcesManager {
    private static final HashMap<IdType, PubCache> CACHE = new HashMap<>();

    private static final HashMap<String, int[]> STYLEABLES = new HashMap<>();


    private enum IdType {
        STRING("string"),
        ID("id"),
        RAW("raw"),
        XML("xml"),
        ATTR("attr"),
        LAYOUT("layout"),
        DRAWABLE("drawable"),
        PLURALS("plurals"),
        COLOR("color"),
        STYLE("style");

        private final String mType;

        IdType(String type) {
            mType = type;
        }

        public String getType() {
            return mType;
        }
    }

    static {
        for (IdType idType : IdType.values()) {
            CACHE.put(idType, new PubCache(200, idType.getType()));
        }

        STYLEABLES.put("CheckBoxPreference", new int[] {android.R.attr.summaryOn,
                android.R.attr.summaryOff, android.R.attr.disableDependentsState,
                getAttrId("disableDependentsState"), getAttrId("summaryOff"),
                getAttrId("summaryOn")});
        STYLEABLES.put("DialogPreference", new int[] {android.R.attr.dialogTitle,
                android.R.attr.dialogMessage, android.R.attr.dialogIcon,
                android.R.attr.positiveButtonText, android.R.attr.negativeButtonText,
                android.R.attr.dialogLayout, getAttrId("dialogIcon"),
                getAttrId("dialogLayout"), getAttrId("dialogMessage"),
                getAttrId("dialogTitle"), getAttrId("negativeButtonText"),
                getAttrId("positiveButtonText")});
        STYLEABLES.put("EditTextPreference", new int[] {getAttrId("useSimpleSummaryProvider")});
        STYLEABLES.put("PreferenceImageView", new int[] {android.R.attr.maxWidth,
                android.R.attr.maxHeight, getAttrId("maxHeight"), getAttrId("maxWidth")});
        STYLEABLES.put("ListPreference", new int[] {android.R.attr.entries, android.R.attr.entryValues,
                getAttrId("entries"), getAttrId("entryValues"),
                getAttrId("useSimpleSummaryProvider")});
        STYLEABLES.put("Preference", new int[] {android.R.attr.icon, android.R.attr.persistent,
                android.R.attr.enabled, android.R.attr.layout,
                android.R.attr.title, android.R.attr.selectable, android.R.attr.key,
                android.R.attr.summary, android.R.attr.order, android.R.attr.widgetLayout,
                android.R.attr.dependency, android.R.attr.defaultValue,
                android.R.attr.shouldDisableView, android.R.attr.fragment,
                getAttrId("allowDividerAbove"), getAttrId("allowDividerBelow"),
                getAttrId("defaultValue"), getAttrId("dependency"),
                getAttrId("enableCopying"), getAttrId("enabled"),
                getAttrId("fragment"), getAttrId("icon"), getAttrId("iconSpaceReserved"),
                getAttrId("isPreferenceVisible"), getAttrId("key"), getAttrId("layout"),
                getAttrId("order"), getAttrId("persistent"), getAttrId("selectable"),
                getAttrId("shouldDisableView"), getAttrId("singleLineTitle"),
                getAttrId("summary"), getAttrId("title"), getAttrId("widgetLayout")});
        STYLEABLES.put("MultiSelectListPreference", new int[] {android.R.attr.entries,
                android.R.attr.entryValues, getAttrId("entries"), getAttrId("entryValues")});
        STYLEABLES.put("PreferenceFragment", new int[] {android.R.attr.layout, android.R.attr.divider,
                android.R.attr.dividerHeight, getAttrId("allowDividerAfterLastItem")});
        STYLEABLES.put("PreferenceFragmentCompat", new int[] {android.R.attr.layout, android.R.attr.divider,
                android.R.attr.dividerHeight, getAttrId("allowDividerAfterLastItem")});
        STYLEABLES.put("PreferenceGroup", new int[] {android.R.attr.orderingFromXml,
                getAttrId("initialExpandedChildrenCount"), getAttrId("orderingFromXml")});
        STYLEABLES.put("BackgroundStyle", new int[] {android.R.attr.selectableItemBackground,
                getAttrId("selectableItemBackground")});
        STYLEABLES.put("SeekBarPreference", new int[] {android.R.attr.layout, android.R.attr.max,
                getAttrId("adjustable"), getAttrId("min"),
                getAttrId("seekBarIncrement"), getAttrId("seekBarValueSuffix"),
                getAttrId("showSeekBarValue"), getAttrId("updatesContinuously")});
        STYLEABLES.put("SwitchPreferenceX", new int[] {android.R.attr.summaryOn, android.R.attr.summaryOff,
                android.R.attr.disableDependentsState, android.R.attr.switchTextOn,
                android.R.attr.switchTextOff, getAttrId("disableDependentsState"),
                getAttrId("summaryOff"), getAttrId("summaryOn"), getAttrId("switchTextOff"),
                getAttrId("switchTextOn")});
        STYLEABLES.put("SwitchPreferenceCompatX", new int[] {android.R.attr.summaryOn,
                android.R.attr.summaryOff, android.R.attr.disableDependentsState,  android.R.attr.switchTextOn,
                android.R.attr.switchTextOff, getAttrId("disableDependentsState"),
                getAttrId("summaryOff"), getAttrId("summaryOn"),
                getAttrId("switchTextOff"), getAttrId("switchTextOn")
        });
    }

    private static class PubCache extends LruCache<String, Integer> {
        private final String mDefType;

        public PubCache(int maxSize, String defType) {
            super(maxSize);
            mDefType = defType;
        }

        @Override
        protected Integer create(String key) {
            Context context = LoaderLS.getInstance();
            Resources resources = context.getResources();
            return resources.getIdentifier(key, mDefType, context.getPackageName());
        }
    }

    private static Integer getIdForType(String name, IdType type) {
        PubCache cache = CACHE.get(type);
        if (cache == null) {
            Logger.error("pubCache not found for type: " + type.getType());
            return null;
        }

        int id = cache.get(name);
        if (id == 0) {
            Logger.warning("id=" + id + " for " + name + ", type=" + type.getType());
        }

        return id;
    }

    public static Integer getId(String name) {
        return getIdForType(name, IdType.ID);
    }

    public static Integer getDrawableId(String name) {
        return getIdForType(name, IdType.DRAWABLE);
    }

    public static Integer getRawId(String name) {
        return getIdForType(name, IdType.RAW);
    }

    public static Integer getXmlId(String name) {
        return getIdForType(name, IdType.XML);
    }

    public static Integer getAttrId(String name) {
        return getIdForType(name, IdType.ATTR);
    }

    public static Integer getColorId(String name) {
        return getIdForType(name, IdType.COLOR);
    }

    public static Integer getStyleId(String name) {
        return getIdForType(name, IdType.STYLE);
    }

    public static Integer getLayoutId(String name) {
        return getIdForType(name, IdType.LAYOUT);
    }

    public static Integer getStringId(String name) {
        return getIdForType(name, IdType.STRING);
    }

    public static Integer getPluralsId(String name) {
        return getIdForType(name, IdType.PLURALS);
    }

    public static String getString(String name, Object... formatArgs) {
        int id = ResourcesManager.getStringId(name);
        if (id == 0) {
            Logger.warning("'" + name + "' not found!");
            return "STRING RESOURCE NOT FOUND: '" + name + "'";
        }
        return LoaderLS.getInstance().getResources().getString(id, formatArgs);
    }

    public static String getQuantityString(String name, int quantity, Object... formatArgs) {
        int id = ResourcesManager.getPluralsId(name);
        if (id == 0) {
            Logger.warning("'" + name + "' not found!");
            return "STRING RESOURCE NOT FOUND: '" + name + "'";
        }

        try {
            return LoaderLS.getInstance().getResources().getQuantityString(id, quantity, formatArgs);
        } catch (Resources.NotFoundException notFoundException) {
            notFoundException.printStackTrace();
            return "QUANTITY STRING NOT FOUNT: '" + name + "'";
        }
    }

    public static String getStringById(Integer id) {
        return LoaderLS.getInstance().getResources().getString(id);
    }

    public static String getString(String name) {
        PubCache cache = CACHE.get(IdType.STRING);

        int resId = cache != null ? cache.get(name) : 0;
        if (resId == 0) {
            return "STRING RESOURCE NOT FOUND: '" + name + "'";
        } else {
            return getStringById(resId);
        }
    }

    public static int[] getStyleableArr(String name) {
        if (TextUtils.isEmpty(name)) {
            Logger.error("empty name");
            return new int[] {};
        }

        return STYLEABLES.get(name);
    }
}
