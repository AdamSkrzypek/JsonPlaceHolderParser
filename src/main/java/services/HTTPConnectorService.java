package services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;


public class HTTPConnectorService {

    private static final String URL_ALL_POSTS = "https://jsonplaceholder.typicode.com/posts";
    private final static Logger logger = LoggerFactory.getLogger(HTTPConnectorService.class);
    private HttpsURLConnection httpsURLConnection;
    private URL url;
    private int httpResponse;
    public HTTPConnectorService(){

    }
    public HttpsURLConnection obtainConnection() {
        try {
            url = new URL(URL_ALL_POSTS);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpResponse = httpsURLConnection.getResponseCode();
            logger.info("connection successful, http response code : " + httpResponse);
        } catch (IOException e) {
            logger.error("unable to establish connection, error code: " + httpResponse);
            logger.error("possible cause of error: " + e);
        }
        return httpsURLConnection;
    }

}
