package configuration;

import controler.JsonController;
import entities.Album;

public class AlbumConversionStrategy implements ConversionStrategy<Album>{

    @Override
    public void execute(JsonController<Album> controller) {
        controller.fetchAll();
        controller.convertToObject();
        controller.persistAll();
    }
}
