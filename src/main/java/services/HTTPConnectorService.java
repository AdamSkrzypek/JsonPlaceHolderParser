package services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;


public class HTTPConnectorService {
    private HttpsURLConnection httpsURLConnection;
    private int httpResponse;
    private  String urlAddress;
    private final static Logger logger = LoggerFactory.getLogger(HTTPConnectorService.class);
    public HTTPConnectorService(String urlAddress){
        this.urlAddress = urlAddress;
    }
    public HttpsURLConnection obtainConnection() {
        try {
            URL url = new URL(urlAddress);
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
