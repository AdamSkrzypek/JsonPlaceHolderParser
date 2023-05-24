package services;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import util.FileUtil;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public  abstract class BasePersistenceService<T> implements PersistenceService<T>{

    private List<T> elements;
    private String directory;
    private Gson gson;
    protected abstract Long getId(T entity);

    protected BasePersistenceService(List<T> elements, String directory, Gson gson) {
        this.elements = elements;
        this.directory = directory;
        this.gson = gson;
    }

    @Override
    public void saveAll() {
        FileUtil.createDirectory(directory);
        elements.stream().forEach(data -> writeToFile(data,directory + getId(data)+".json"));
    }
    private void writeToFile(T data, String directory) {
        try {
            FileWriter fileWriter =new FileWriter(directory);
            gson.toJson(data,fileWriter);
            log.info("persisting object "  + " to directory: " + directory);
            fileWriter.close();
        } catch (IOException e) {
            log.error("error while saving object{}" , e);
        }
    }
}
