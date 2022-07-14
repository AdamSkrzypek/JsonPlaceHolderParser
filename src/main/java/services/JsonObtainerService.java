package services;
import java.io.*;


public class JsonObtainerService {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rawJson;
    }
}
