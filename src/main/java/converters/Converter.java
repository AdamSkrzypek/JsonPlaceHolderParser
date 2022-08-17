package converters;

import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class Converter<T>{
    Supplier<String> source;
    private List<T> elementList = new ArrayList<>();

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final static Logger logger = LoggerFactory.getLogger(Converter.class);


    protected Converter(Supplier<String> source) {
        this.source = source;
    }
    public List<T> convertToCorrectObject(Supplier<String> source,Class<T>cls) {
        JsonArray array = JsonParser.parseString(source.get()).getAsJsonArray();
        for (JsonElement jsonElement : array) {
            elementList.add(gson.fromJson(jsonElement, cls));
            logger.info("converting json: " + jsonElement );
        }
        return elementList;

    }
    public List<T> getPostList() {
        return elementList;
    }

}