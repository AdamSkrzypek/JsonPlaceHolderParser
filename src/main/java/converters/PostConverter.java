package converters;

import entities.Post;
import services.JsonObtainerService;

import java.util.function.Supplier;

public class PostConverter extends Converter<Post> {

    public PostConverter(Supplier<String> source) {
        super(source);
    }
}
