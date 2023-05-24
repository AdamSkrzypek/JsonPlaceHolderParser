package configuration;

import controler.JsonController;

public interface ConversionStrategy<T> {
    void execute(JsonController<T> controller);
}
