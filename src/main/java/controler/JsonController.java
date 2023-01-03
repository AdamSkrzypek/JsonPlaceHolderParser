package controler;

import com.google.gson.JsonArray;
import services.HTTPConnectorService;

import java.util.List;
import java.util.function.Function;

public interface JsonController<T> {
   Function<HTTPConnectorService,JsonArray> fetchAll();
   void persistAll();
   List<T> convertToObject();
}
