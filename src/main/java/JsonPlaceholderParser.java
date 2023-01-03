
import configuration.JsonControllerStrategy;
import converters.ArgumentConverter;
import exceptions.ObjectTypeException;


public class JsonPlaceholderParser {
    public static void main(String[] args) throws ObjectTypeException {



        ArgumentConverter argumentConverter = new ArgumentConverter();
        argumentConverter.mapArguments(args);

        JsonControllerStrategy strategy = new JsonControllerStrategy();
        strategy.chooseStrategy(argumentConverter);

    }
}
