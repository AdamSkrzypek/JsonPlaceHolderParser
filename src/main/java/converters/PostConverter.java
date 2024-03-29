package converters;

import com.google.gson.JsonArray;
import entities.Post;
import services.HTTPConnectorService;

import java.util.function.Function;

public class PostConverter extends Converter<Post> {
    public PostConverter(Function<HTTPConnectorService, JsonArray> source) {
        super(source);
    }
}
