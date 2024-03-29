package services;

import com.google.gson.Gson;
import entities.Album;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
public class AlbumPersistenceService extends BasePersistenceService<Album> {

    @Override
    protected Long getId(Album entity) {
        return entity.getId();
    }

    public AlbumPersistenceService(List<Album> elements, String directory, Gson gson) {
        super(elements, directory, gson);
    }
}
