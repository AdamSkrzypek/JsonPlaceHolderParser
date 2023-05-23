package controler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import converters.AlbumConverter;
import converters.ArgumentConverter;
import converters.Converter;
import entities.Album;
import lombok.extern.slf4j.Slf4j;
import services.BasePersistenceService;
import services.AlbumPersistenceService;
import services.HTTPConnectorService;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class AlbumController extends BaseController<Album>{


    public AlbumController(ArgumentConverter argumentConverter) {
        super(argumentConverter);
    }

    @Override
    protected Converter<Album> createConverter(Function<HTTPConnectorService, JsonArray> function) {
        return new AlbumConverter(function);
    }

    @Override
    protected Class<Album> getEntity() {
        return Album.class;
    }

    @Override
    protected BasePersistenceService<Album> createPersistenceService(List<Album> elementList, String directory, Gson gson) {
        return new AlbumPersistenceService(elementList,directory,gson);
    }
}
