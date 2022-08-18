package controler;

import converters.ArgumentConverter;
import converters.Converter;
import converters.PostConverter;
import entities.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.JsonObtainerService;
import services.PostPersistenceService;
import java.util.List;
import java.util.function.Supplier;

public class PostController implements JsonController<Post> {

    private JsonObtainerService  jsonObtainerService =  new JsonObtainerService(ArgumentConverter.getUrl());
    private Supplier<String> supplier =  jsonObtainerService::getJsonFromURLAsString;
    private Converter<Post>  postConverter = new PostConverter(supplier);
    private PostPersistenceService postPersistenceService = new PostPersistenceService(postConverter);
    private final static Logger logger = LoggerFactory.getLogger(PostController.class);


    public PostController() {
        //C.
    }
    @Override
    public Supplier<String> fetchAllPosts() {
      logger.info("sending request to JsonObtainerService");
      return supplier;

    }
    @Override
    public List<Post> convertToObject() {
        logger.info("sending request to convert raw JSON's to Post objects");
        postConverter.convertToCorrectObject(supplier,Post.class);
        return postConverter.getPostList();
    }

    @Override
    public void persistAllPosts() {
        logger.info("sending request to PostPersistenceService");
        postPersistenceService.saveAllPostsToSingleFile(ArgumentConverter.getDirectory());
    }

}
