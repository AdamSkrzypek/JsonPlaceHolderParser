package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import converters.Converter;
import entities.Post;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PostPersistenceService {
    private static final String outputPostsDirectory= "C:\\outputAllPosts";
    private static final String jsonFormat = ".json";

    private Converter<Post> postConverter;
    private FileWriter fileWriter;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public PostPersistenceService(Converter<Post> postConverter) {
        this.postConverter = postConverter;
    }

    public void saveAllPostsToSingleFile() {
        createDirectory(outputPostsDirectory);
        for (int i = 0; i <postConverter.getPostList().size() ; i++) {
            String postId = postConverter.getPostList().get(i).getId().toString();
            String outputDestination = outputPostsDirectory+ "\\"+ postId + jsonFormat;
            try {
                fileWriter = new FileWriter(outputDestination);
                gson.toJson(postConverter.getPostList().get(i),fileWriter);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
         }

    }

    private boolean createDirectory(String outputPostsDirectory){
        return new File(outputPostsDirectory).mkdir();
    }
}
