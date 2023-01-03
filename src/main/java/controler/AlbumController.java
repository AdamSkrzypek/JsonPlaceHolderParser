package controler;

import com.google.gson.JsonArray;
import converters.AlbumConverter;
import converters.ArgumentConverter;
import converters.Converter;
import entities.Album;
import lombok.extern.slf4j.Slf4j;
import services.AlbumPersistenceService;
import services.HTTPConnectorService;
import services.JsonObtainerService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class AlbumController implements JsonController<Album>{
    private ArgumentConverter argumentConverter;
    private Function<HTTPConnectorService,JsonArray> function;
    private Converter<Album> albumConverter;

    public AlbumController(ArgumentConverter argumentConverter) {
        this.argumentConverter=argumentConverter;
    }

    @Override
    public Function<HTTPConnectorService, JsonArray> fetchAll() {
        String connectionUrl = argumentConverter.getUrl();
        if (connectionUrl !=null){
            JsonObtainerService jsonObtainerService = new JsonObtainerService(connectionUrl, new JsonArray());
            function = jsonObtainerService::readJsonFromURLs;
            log.info("sending request to JsonObtainerService");
        }
        return function;
    }

    @Override
    public List<Album> convertToObject() {
        log.info("sending request to convert raw JSON's to Album objects");
        albumConverter = new AlbumConverter(function);
        albumConverter.convertToCorrectObject(Album.class);
        List<Album> albumList = albumConverter.getElementList();
        return albumList.isEmpty() ? new ArrayList<>(): albumList;
    }

    @Override
    public void persistAll() {
        String directory = argumentConverter.getDirectory();
        if (directory !=null){
            log.info("sending request to AlbumPersistenceService");
            AlbumPersistenceService albumPersistenceService = new AlbumPersistenceService();
            albumPersistenceService.saveAll(albumConverter.getElementList(),directory);
        }
    }
}
