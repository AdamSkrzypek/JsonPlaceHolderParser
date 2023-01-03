package services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import converters.ArgumentConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;
import testdata.PostTestData;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;


class JsonObtainerServiceTest {
    private JsonArray jsonFromURL;
    private PostTestData testData;
    private JsonObtainerService jsonObtainerService;
    @Mock
    HTTPConnectorService httpConnectorService = Mockito.mock(HTTPConnectorService.class);
    @Mock
    HttpsURLConnection urlConnection = Mockito.mock(HttpsURLConnection.class);
    @Mock
    ArgumentConverter argumentConverter =Mockito.mock(ArgumentConverter.class);

    @BeforeEach
    public void setup() throws IOException {

        MockitoAnnotations.openMocks(this);
        jsonFromURL = new JsonArray();
        jsonObtainerService = new JsonObtainerService("url",jsonFromURL);
        testData = new PostTestData();
        Mockito.when(argumentConverter.getUrl()).thenReturn("url");
        Mockito.when(httpConnectorService.obtainConnection("url")).thenReturn(urlConnection);
        Mockito.when(urlConnection.getInputStream()).thenReturn(testData.getFakePostsData());

    }

    @Test
    void testJSONTypeAndSize() {
        //when
        jsonFromURL = jsonObtainerService.readJsonFromURLs(httpConnectorService);

        //then
        Assertions.assertAll(
                () -> Assertions.assertTrue(jsonFromURL.isJsonArray()),
                () -> Assertions.assertFalse(jsonFromURL.isJsonObject()),
                () -> Assertions.assertEquals(100, jsonFromURL.size())
        );

    }
    @Test
    void testJsonValidity(){
        //when
        jsonFromURL = jsonObtainerService.readJsonFromURLs(httpConnectorService);

        for (int i = 0; i < jsonFromURL.size() ; i++) {
            JsonObject jsonObject = (JsonObject) jsonFromURL.get(i);
            //then
            Assertions.assertAll(
                    () -> Assertions.assertTrue(jsonObject.has("userId")),
                    () -> Assertions.assertTrue(jsonObject.has("id")),
                    () -> Assertions.assertTrue(jsonObject.has("title")),
                    () -> Assertions.assertTrue(jsonObject.has("body"))
            );
        }
    }


}