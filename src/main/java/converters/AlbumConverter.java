package converters;

import com.google.gson.JsonArray;
import entities.Album;
import services.HTTPConnectorService;

import java.util.function.Function;

public class AlbumConverter extends Converter<Album>{
    public AlbumConverter(Function<HTTPConnectorService, JsonArray> source) {
        super(source);
    }
}
