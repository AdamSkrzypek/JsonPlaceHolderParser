package configuration;

import controler.AlbumController;
import controler.JsonController;
import controler.PostController;
import converters.ArgumentConverter;
import entities.Album;
import entities.Post;
import exceptions.ObjectTypeException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonControllerStrategy {

    public void chooseStrategy(ArgumentConverter argumentConverter) throws ObjectTypeException {
        String type = argumentConverter.getType().toLowerCase();
        boolean isTypeAndUrlMatch = argumentConverter.getUrl().endsWith(type);

        if (isTypeAndUrlMatch){
                switch(type){
                    case "posts" -> {
                        log.info("strategy post conversion");
                        JsonController<Post> postController = new PostController(argumentConverter);
                        postController.fetchAll();
                        postController.convertToObject();
                        postController.persistAll();
                    }
                    case "albums" -> {
                        log.info("strategy album conversion");
                        JsonController<Album> albumController = new AlbumController(argumentConverter);
                        albumController.fetchAll();
                        albumController.convertToObject();
                        albumController.persistAll();
                    }
                    default -> throw new ObjectTypeException("Invalid object type provided");
                }
        }
    }
}
