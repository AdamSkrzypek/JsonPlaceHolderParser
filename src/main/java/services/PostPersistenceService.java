package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entities.Post;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@NoArgsConstructor
@Slf4j
public class PostPersistenceService implements PersistenceService<Post>{
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void saveAll(List<Post> elements, String directory) {
        FileUtil.createDirectory(directory);
        elements.stream().forEach(post -> writeToFile(post,directory+post.getId()+jsonFormat));
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
