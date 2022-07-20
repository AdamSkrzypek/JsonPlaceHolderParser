
import controler.JsonController;
import controler.PostController;
import converters.Converter;
import converters.PostConverter;
import entities.Post;
import services.JsonObtainerService;
import services.PostPersistenceService;

import java.util.function.Supplier;

public class JsonPlaceholderParser {
    public static void main(String[] args) {
        JsonObtainerService jsonObtainerService = new JsonObtainerService();
        Supplier<String> supplier = jsonObtainerService::getJsonFromURLAsString;
        Converter<Post> postConverter= new PostConverter(supplier);
        PostPersistenceService postPersistenceService = new PostPersistenceService(postConverter);


        JsonController postController = new PostController(jsonObtainerService,postPersistenceService);
        postController.fetchAllPosts();
        postConverter.convertToCorrectObject(supplier, Post.class);
        postController.persistAllPosts();
    }
}
