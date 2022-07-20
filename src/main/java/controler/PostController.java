package controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.JsonObtainerService;
import services.PostPersistenceService;

public class PostController implements JsonController {

   private final static Logger logger = LoggerFactory.getLogger(PostPersistenceService.class);
   private JsonObtainerService jsonObtainerService;
   private PostPersistenceService postPersistenceService;
    public PostController(JsonObtainerService jsonObtainerService, PostPersistenceService postPersistenceService) {
        this.postPersistenceService = postPersistenceService;
        this.jsonObtainerService = jsonObtainerService;
    }
    @Override
    public String fetchAllPosts() {
      logger.info("sending request to JsonObtainerService");
      return jsonObtainerService.getJsonFromURLAsString();

    }
    @Override
    public void persistAllPosts() {
        logger.info("sending request to PostPersistenceService");
        postPersistenceService.saveAllPostsToSingleFile();

    }
}
