package converters;

import entities.Post;
import services.JsonObtainerService;

public class PostConverter extends Converter<Post> {

    public PostConverter(JsonObtainerService jsonObtainerService) {
        super(jsonObtainerService);
    }
}
