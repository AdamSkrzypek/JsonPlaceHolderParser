package services;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import converters.ArgumentConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

@AllArgsConstructor
@Slf4j
@Getter
public class JsonObtainerService {
    private String connectionUrl;
    private JsonArray jsonArray;
    public JsonArray readJsonFromURLs(HTTPConnectorService httpConnector) {
        try {
                InputStream inputStream = new BufferedInputStream(getInputStream(httpConnector));
                String rawJson = new String(inputStream.readAllBytes());
                jsonArray.addAll(JsonParser.parseString(rawJson).getAsJsonArray());
                log.info("obtaining json from server");

        } catch (IOException e) {
            log.error("error occurred while obtaining json : {}" , e);
        }
        return jsonArray;
    }

    private InputStream getInputStream(HTTPConnectorService httpConnector) throws IOException {
        return httpConnector.obtainConnection(connectionUrl).getInputStream();
    }
}
