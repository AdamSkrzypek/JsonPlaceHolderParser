package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entities.Post;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import util.FileUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class PostPersistenceService implements PersistenceService{

    private List<Post> elements;
    private String directory;

    private  Gson gson;
    @Override
    public void saveAll() {
        FileUtil.createDirectory(directory);
        elements.stream().forEach(post -> writeToFile(post,directory+post.getId()+".json"));
    }

    private void writeToFile(Post post, String directory) {
          try {
            FileWriter fileWriter = new FileWriter(directory);
            gson.toJson(post, fileWriter);
            log.info("persisting post " + post.getId() + " to directory: " + directory);
            fileWriter.close();
        } catch (IOException e) {
            log.error("error while saving post" + e);
        }
    }
}
