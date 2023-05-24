package controler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import converters.ArgumentConverter;
import converters.Converter;
import lombok.extern.slf4j.Slf4j;
import services.BasePersistenceService;
import services.HTTPConnectorService;
import services.JsonObtainerService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
public abstract class BaseController<T> implements JsonController<T>{

    private ArgumentConverter argumentConverter;
    private Function<HTTPConnectorService,JsonArray> function;
    private Converter<T> postConverter;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected abstract Converter<T> createConverter(Function<HTTPConnectorService, JsonArray> function);
    protected abstract Class<T> getEntity();
    protected abstract BasePersistenceService<T> createPersistenceService(List<T> elementList, String directory, Gson gson) ;

    public BaseController(ArgumentConverter argumentConverter) {
        this.argumentConverter = argumentConverter;
    }

    @Override
    public Function<HTTPConnectorService, JsonArray> fetchAll() {
        String connectionUrl = argumentConverter.getUrl();
        if (connectionUrl !=null){
            JsonObtainerService jsonObtainerService =  new JsonObtainerService(connectionUrl,new JsonArray());
            function = jsonObtainerService::readJsonFromURLs;
            log.info("sending request to JsonObtainerService");
        }
        return function;
    }

    @Override
    public void persistAll() {
        String directory = argumentConverter.getDirectory();
        if(directory !=null){
            log.info("sending request to PersistenceService");
            BasePersistenceService<T> persistenceService = createPersistenceService(postConverter.getElementList(),directory,gson);
            persistenceService.saveAll();
        }
    }



    @Override
    public List<T> convertToObject() {
        log.info("sending request to convert raw JSON's to Post objects");
        postConverter = createConverter(function);
        postConverter.convertToCorrectObject(getEntity(),gson);
        List<T> postList =postConverter.getElementList();
        return postList.isEmpty() ? new ArrayList<>(): postList;
    }
}
