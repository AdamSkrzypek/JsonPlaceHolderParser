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

    private JsonObtainerService jsonObtainerService;
    private Supplier<String> supplier =  jsonObtainerService::getJsonFromURLAsString;
    private Converter<Post> postConverter;
    private PostPersistenceService postPersistenceService;
    private final static Logger logger = LoggerFactory.getLogger(PostController.class);


    public PostController() {
        //C.
    }


    @Override
    public Supplier<String> fetchAllPosts() {
      jsonObtainerService =  new JsonObtainerService(ArgumentConverter.getUrl());
      logger.info("sending request to JsonObtainerService");
      return supplier;

    }
    @Override
    public List<Post> convertToObject() {
        postConverter = new PostConverter(supplier);
        logger.info("sending request to convert raw JSON's to Post objects");
        postConverter.convertToCorrectObject(supplier,Post.class);
        return postConverter.getPostList();
    }

    @Override
    public void persistAllPosts() {
        postPersistenceService = new PostPersistenceService(postConverter);
        logger.info("sending request to PostPersistenceService");
        postPersistenceService.saveAllPostsToSingleFile(ArgumentConverter.getDirectory());

    }

}
