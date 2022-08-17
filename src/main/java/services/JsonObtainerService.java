package services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class JsonObtainerService {
    private final String url;
    private String rawJson;
    private final static Logger logger = LoggerFactory.getLogger(JsonObtainerService.class);
    public JsonObtainerService(String url){
        this.url = url;
    }
    public String getJsonFromURLAsString(){
        try {
            HTTPConnectorService  httpConnector = new HTTPConnectorService(url);
            InputStream inputStream = new BufferedInputStream( httpConnector.obtainConnection().getInputStream());
            rawJson = new String(inputStream.readAllBytes());
            logger.info("obtaining json from server" );

        } catch (IOException e) {
            logger.error("error occurred while obtaining json" + e);
        }
        return rawJson;
    }
}
