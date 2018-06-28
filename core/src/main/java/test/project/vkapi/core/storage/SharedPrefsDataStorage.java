package test.project.vkapi.core.storage;

import android.content.SharedPreferences;

public class SharedPrefsDataStorage  implements DataStorage {
    private SharedPreferences preferences;

    public SharedPrefsDataStorage(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void save(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    @Override
    public String get(String key) {
        return preferences.getString(key, null);
    }
}
