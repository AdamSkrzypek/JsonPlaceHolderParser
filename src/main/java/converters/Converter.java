package converters;

import com.google.gson.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.HTTPConnectorService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Slf4j
public abstract class Converter<T>{
    private Function<HTTPConnectorService,JsonArray> source;
    private List<T> elementList = new ArrayList<>();

    protected Converter(Function<HTTPConnectorService,JsonArray> source) {
        this.source = source;
    }
    public List<T> convertToCorrectObject(Class<T>cls,Gson gson) {
        for (JsonElement jsonElement : source.apply(new HTTPConnectorService())) {
            elementList.add(gson.fromJson(jsonElement, cls));
            log.info("converting json: {}", jsonElement );
        }
        return elementList;
    }
    public List<T> getElementList() {
        return elementList;
    }

}