package services;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

@NoArgsConstructor
@Getter
public class HTTPConnectorService {
    private final static Logger logger = LoggerFactory.getLogger(HTTPConnectorService.class);
    private HttpsURLConnection httpsURLConnection;
    public HttpsURLConnection obtainConnection(String urlAddress) throws IOException {
        try {
            URL url = new URL(urlAddress);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            logger.info("connection successful, http response code : {}" , httpsURLConnection.getResponseCode());
        } catch (IOException e) {
            logger.error("unable to establish connection, error code: {}", httpsURLConnection.getResponseCode());
            logger.error("possible cause of error: {}", e);
        }
        return httpsURLConnection;
    }

}
