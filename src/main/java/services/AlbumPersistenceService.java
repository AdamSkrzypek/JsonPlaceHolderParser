package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Album;
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
public class AlbumPersistenceService implements PersistenceService<Album>{
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void saveAll(List<Album> elements, String directory) {
        FileUtil.createDirectory(directory);
        elements.stream().forEach(album -> writeToFile(album,directory + album.getId()+jsonFormat));
    }
    private void writeToFile(Album album, String directory) {
        try {
            FileWriter fileWriter =new FileWriter(directory);
            gson.toJson(album,fileWriter);
            log.info("persisting album " + album.getId() + " to directory: " + directory);
            fileWriter.close();
        } catch (IOException e) {
            log.error("error while saving album" + e);
        }
    }
}
