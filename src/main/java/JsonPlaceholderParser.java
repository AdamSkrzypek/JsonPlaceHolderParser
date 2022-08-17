
import controler.JsonController;
import controler.PostController;
import converters.ArgumentConverter;
import entities.Post;

public class JsonPlaceholderParser {
    public static void main(String[] args) {

        String url= "url=https://jsonplaceholder.typicode.com/posts";
        String directory ="directory=C:\\OutputPosts\\";
        String[]arguments = {url,directory};
        ArgumentConverter.mapArguments(arguments);
        JsonController<Post> postController = new PostController();
        postController.fetchAllPosts();
        postController.convertToObject();
        postController.persistAllPosts();


    }
}
