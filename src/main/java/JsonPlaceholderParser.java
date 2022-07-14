
import controler.JsonController;
import controler.PostController;
import converters.Converter;
import converters.PostConverter;
import entities.Post;
import services.JsonObtainerService;
import services.PostPersistenceService;

public class JsonPlaceholderParser {
    public static void main(String[] args) {
        JsonObtainerService jsonObtainerService = new JsonObtainerService();
        Converter<Post> postConverter= new PostConverter(jsonObtainerService);
        PostPersistenceService postPersistenceService = new PostPersistenceService(postConverter);
        JsonController postController = new PostController(jsonObtainerService,postPersistenceService);
        postController.fetchAllPosts();
        postConverter.convertToCorrectObject(jsonObtainerService, Post.class);
        postController.persistAllPosts();
    }
}
