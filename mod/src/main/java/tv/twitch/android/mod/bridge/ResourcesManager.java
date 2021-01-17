package tv.twitch.android.mod.bridge;


import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.LruCache;

import java.util.HashMap;

import tv.twitch.android.mod.util.Logger;


public class ResourcesManager {
    private static final HashMap<IdType, PubCache> CACHE = new HashMap<>();

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
}
