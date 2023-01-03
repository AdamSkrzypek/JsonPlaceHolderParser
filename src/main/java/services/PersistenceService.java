package services;

import java.util.List;

public interface PersistenceService<T> {
    String jsonFormat = ".json";
    void saveAll(List<T> elements, String directory);
}
