package controler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import converters.ArgumentConverter;
import converters.Converter;
import converters.PostConverter;
import entities.Post;
import lombok.extern.slf4j.Slf4j;
import services.HTTPConnectorService;
import services.JsonObtainerService;
import services.PostPersistenceService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class PostController implements JsonController<Post> {

    private ArgumentConverter argumentConverter;
    private Function<HTTPConnectorService,JsonArray> function;
    private Converter<Post>  postConverter;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public PostController(ArgumentConverter argumentConverter) {
        this.argumentConverter = argumentConverter;
    }

    @Override
    public Function<HTTPConnectorService,JsonArray> fetchAll() {
      String connectionUrl = argumentConverter.getUrl();
      if (connectionUrl !=null){
          JsonObtainerService  jsonObtainerService =  new JsonObtainerService(connectionUrl,new JsonArray());
          function = jsonObtainerService::readJsonFromURLs;
          log.info("sending request to JsonObtainerService");
      }
      return function;

    }
    @Override
    public List<Post> convertToObject() {
        log.info("sending request to convert raw JSON's to Post objects");
        postConverter = new PostConverter(function);
        postConverter.convertToCorrectObject(Post.class,gson);
        List<Post> postList =postConverter.getElementList();
        return postList.isEmpty() ? new ArrayList<>(): postList;
    }

    @Override
    public void persistAll() {
        String directory = argumentConverter.getDirectory();
        if (directory !=null ){
            log.info("sending request to PostPersistenceService");
            PostPersistenceService postPersistenceService = new PostPersistenceService(postConverter.getElementList(),directory,gson);
            postPersistenceService.saveAll();
        }
    }


}
