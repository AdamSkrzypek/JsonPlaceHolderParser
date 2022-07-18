package converters;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class Converter<T>{
    List<T> elementList = new ArrayList<>();
    Supplier<String> source;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Converter(Supplier<String> source) {
        this.source = source;
    }
    public List<T> convertToCorrectObject(Supplier<String> source,Class<T>cls) {
        JsonArray array = JsonParser.parseString(source.get()).getAsJsonArray();
        for (JsonElement jsonElement : array) {
            elementList.add(gson.fromJson(jsonElement, cls));
        }
        return elementList;

    }


    public List<T> getPostList() {
        return elementList;
    }

}