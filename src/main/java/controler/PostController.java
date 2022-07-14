package controler;

import services.JsonObtainerService;
import services.PostPersistenceService;

public class PostController implements JsonController {
   private JsonObtainerService jsonObtainerService;
   private PostPersistenceService postPersistenceService;
    public PostController(JsonObtainerService jsonObtainerService, PostPersistenceService postPersistenceService) {
        this.postPersistenceService = postPersistenceService;
        this.jsonObtainerService = jsonObtainerService;
    }
    @Override
    public String fetchAllPosts() {
      return jsonObtainerService.getJsonFromURLAsString();

    }
    @Override
    public void persistAllPosts() {
        postPersistenceService.saveAllPostsToSingleFile();

    }
}
