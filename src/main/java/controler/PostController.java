package controler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import converters.ArgumentConverter;
import converters.Converter;
import converters.PostConverter;
import entities.Post;
import lombok.extern.slf4j.Slf4j;
import services.BasePersistenceService;
import services.HTTPConnectorService;
import services.PostPersistenceService;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class PostController extends BaseController<Post> {


    public PostController(ArgumentConverter argumentConverter) {
        super(argumentConverter);
    }

    @Override
    protected Converter<Post> createConverter(Function<HTTPConnectorService, JsonArray> function) {
        return new PostConverter(function);
    }

    @Override
    protected Class<Post> getEntity() {
        return Post.class;
    }

    @Override
    protected BasePersistenceService<Post> createPersistenceService(List<Post> elementList, String directory, Gson gson) {
        return new PostPersistenceService(elementList,directory,gson);
    }
}
