package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Album;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.appender.rolling.FileExtension;
import util.FileUtil;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class AlbumPersistenceService implements PersistenceService{
    private List<Album> elements;
    private String directory;
    private Gson gson;
    @Override
    public void saveAll() {
        FileUtil.createDirectory(directory);
        elements.stream().forEach(album -> writeToFile(album,directory + album.getId()+ ".json"));
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
