package test.project.vkapi.core.storage;

public interface DataStorage {
    void save(String key, String value);
    String get(String key);
}
