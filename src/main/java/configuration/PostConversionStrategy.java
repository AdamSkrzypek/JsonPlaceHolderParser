package configuration;

import controler.JsonController;
import entities.Post;

public class PostConversionStrategy implements ConversionStrategy<Post> {

    @Override
    public void execute(JsonController<? extends Post> controller) {
        controller.fetchAll();
        controller.convertToObject();
        controller.persistAll();
    }
}
