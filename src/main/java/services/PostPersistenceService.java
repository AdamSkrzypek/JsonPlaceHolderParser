package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import converters.Converter;
import entities.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PostPersistenceService {
    private final static Logger logger = LoggerFactory.getLogger(PostPersistenceService.class);
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
        List<Post> postList = postConverter.getPostList();
        for (Post singlePost : postList) {
            String postId = singlePost.getId().toString();
            String outputDestination = outputPostsDirectory + "\\" + postId + jsonFormat;
            try {
                fileWriter = new FileWriter(outputDestination);
                gson.toJson(singlePost, fileWriter);
                logger.info("persisting post " + singlePost.getId() + " to directory: " + outputDestination);
                fileWriter.close();
            } catch (IOException e) {
                logger.error("error while saving post" + e);
            }
        }

    }

    private boolean createDirectory(String outputPostsDirectory){
        File file = new File(outputPostsDirectory);
        if (file.mkdir()){
            logger.info(" new output directory is created: " + outputPostsDirectory);
        }
        logger.info("output directory: " + outputPostsDirectory);
        return false;
    }
}
