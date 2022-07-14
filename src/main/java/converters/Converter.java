package converters;

import com.google.gson.*;
import entities.Post;
import services.JsonObtainerService;

import java.util.ArrayList;
import java.util.List;

public abstract class Converter<T extends Post>{
    List<T> elementList = new ArrayList<>();
    JsonObtainerService jsonObtainerService;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Converter(JsonObtainerService jsonObtainerService) {
        this.jsonObtainerService = jsonObtainerService;
    }
    public List<T> convertToCorrectObject(JsonObtainerService jsonObtainerService,Class<T>cls) {
        JsonArray array = JsonParser.parseString(jsonObtainerService.getJsonFromURLAsString()).getAsJsonArray();
        for (JsonElement jsonElement : array) {
            elementList.add(gson.fromJson(jsonElement, cls));
        }
        return elementList;

    }
    public List<T> getPostList() {
        return elementList;
    }

}