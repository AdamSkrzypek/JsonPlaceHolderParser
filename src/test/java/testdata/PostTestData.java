package testdata;

import com.google.gson.JsonArray;
import entities.Post;
import services.JsonObtainerService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PostTestData {
    public static final String FAKE_POSTS_JSON_PATH = "src/test/java/testdata/fakePost.json";

    public InputStream getFakePostsData() throws FileNotFoundException {
        File file  = new File(FAKE_POSTS_JSON_PATH);
        InputStream inputStream =  new FileInputStream(file);
        return inputStream;
    }

    public JsonArray getJson() throws IOException {
        JsonArray jsonArray = new JsonArray();
        String str = new String(getFakePostsData().readAllBytes());
        jsonArray.add(str);
        return jsonArray;
    }


}
