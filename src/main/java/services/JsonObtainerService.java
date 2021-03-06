package services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class JsonObtainerService {
    private final static Logger logger = LoggerFactory.getLogger(JsonObtainerService.class);
    private String rawJson;
    private HTTPConnectorService httpConnector;
    private InputStream inputStream;
    public JsonObtainerService(){
    }
    public String getJsonFromURLAsString(){
        try {
            httpConnector = new HTTPConnectorService();
            inputStream = new BufferedInputStream( httpConnector.obtainConnection().getInputStream());
            rawJson = new String(inputStream.readAllBytes());
            logger.info("obtaining json from server" );

        } catch (IOException e) {
            logger.error("error occurred while obtaining json" + e);
            e.printStackTrace();
        }
        return rawJson;
    }
}
