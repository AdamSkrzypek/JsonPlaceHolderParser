package services;

import com.google.gson.Gson;

import entities.Post;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class PostPersistenceService extends BasePersistenceService<Post> {

    public PostPersistenceService(List<Post> elements, String directory, Gson gson) {
        super(elements, directory, gson);
    }

}
