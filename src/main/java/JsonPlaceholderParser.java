
import configuration.JsonControllerStrategy;
import converters.ArgumentConverter;
import exceptions.ObjectTypeException;


public class JsonPlaceholderParser {
    public static void main(String[] args) throws ObjectTypeException {

        String urlPosts= "url=https://jsonplaceholder.typicode.com/posts";
        String urlAlbums = "url=https://jsonplaceholder.typicode.com/albums";
        String directoryPosts ="directory=C:\\OutputPosts\\";
        String directoryAlbums ="directory=C:\\OutputAlbums\\";
        String objectType ="type=albums";
        String[]arguments = {urlAlbums,directoryAlbums,objectType};

        ArgumentConverter argumentConverter = new ArgumentConverter();
        argumentConverter.mapArguments(arguments);

        JsonControllerStrategy strategy = new JsonControllerStrategy();
        strategy.chooseStrategy(argumentConverter);

    }
}
