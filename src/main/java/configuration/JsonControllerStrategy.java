package configuration;

import controler.AlbumController;
import controler.JsonController;
import controler.PostController;
import converters.ArgumentConverter;
import exceptions.ObjectTypeException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JsonControllerStrategy {
    private Map<String, ConversionStrategy<?>> strategies;

    public JsonControllerStrategy() {
        strategies = new HashMap<>();
        strategies.put("posts", new PostConversionStrategy());
        strategies.put("albums", new AlbumConversionStrategy());
    }

    public <T> void chooseStrategy(ArgumentConverter argumentConverter) throws ObjectTypeException {
        String type = argumentConverter.getType().toLowerCase();
        boolean isTypeAndUrlMatch = argumentConverter.getUrl().endsWith(type);

        if (isTypeAndUrlMatch) {
            ConversionStrategy<T> strategy = (ConversionStrategy<T>) strategies.get(type);
            if (strategy != null) {
                log.info("Strategy " + type + " conversion");
                JsonController<T> controller = createController(type, argumentConverter);
                executeStrategy(strategy, controller);
            } else {
                throw new ObjectTypeException("Invalid object type provided");
            }
        }
    }

    private <T> void executeStrategy(ConversionStrategy<T> strategy, JsonController<T> controller) {
        strategy.execute(controller);
    }

    private <T> JsonController<T> createController(String type, ArgumentConverter argumentConverter) {
        if ("posts".equals(type)) {
            return (JsonController<T>) new PostController(argumentConverter);
        } else if ("albums".equals(type)) {
            return (JsonController<T>) new AlbumController(argumentConverter);
        }
        return null;
    }
}