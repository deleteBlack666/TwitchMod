package tv.twitch.android.mod.preference.adapter;


import androidx.annotation.Nullable;

import java.util.Set;

import tv.twitch.android.mod.libs.binaryprefs.Preferences;
import tv.twitch.android.mod.libs.preference.PreferenceDataStore;


public class PreferenceDataStoreBinaryAdapter extends PreferenceDataStore {
    private final Preferences binaryPreferences;

    public PreferenceDataStoreBinaryAdapter(Preferences binaryPreferences) {
        this.binaryPreferences = binaryPreferences;
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return binaryPreferences.getBoolean(key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return binaryPreferences.getFloat(key, defValue);
    }

    @Override
    public int getInt(String key, int defValue) {
        return binaryPreferences.getInt(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return binaryPreferences.getLong(key, defValue);
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
        return binaryPreferences.getStringSet(key, defValues);
    }

    @Nullable
    @Override
    public String getString(String key, @Nullable String defValue) {
        return binaryPreferences.getString(key, defValue);
    }

    @Override
    public void putBoolean(String key, boolean value) {
        binaryPreferences.edit().putBoolean(key, value).apply();
    }

    @Override
    public void putFloat(String key, float value) {
        binaryPreferences.edit().putFloat(key, value).apply();
    }

    @Override
    public void putInt(String key, int value) {
        binaryPreferences.edit().putInt(key, value).apply();
    }

    @Override
    public void putLong(String key, long value) {
        binaryPreferences.edit().putLong(key, value).apply();
    }

    @Override
    public void putString(String key, @Nullable String value) {
        binaryPreferences.edit().putString(key, value).apply();
    }

    @Override
    public void putStringSet(String key, @Nullable Set<String> values) {
        binaryPreferences.edit().putStringSet(key, values).apply();
    }
}
