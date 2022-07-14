package services;



import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

public class HTTPConnectorService {

    private static final String URL_ALL_POSTS = "https://jsonplaceholder.typicode.com/posts";
    private HttpsURLConnection httpsURLConnection;
    private URL url;
    public HTTPConnectorService(){

    }
    public HttpsURLConnection obtainConnection(){
        try {
            url = new URL(URL_ALL_POSTS);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpsURLConnection;
    }

}
