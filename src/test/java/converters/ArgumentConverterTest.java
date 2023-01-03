package converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentConverterTest {

    @Test
    @DisplayName("mapping url and directory property")

    void mapUrlAndDirectoryArgumentsFromConsole() {
        String url= "url=https://jsonplaceholder.typicode.com/posts";
        String directory ="directory=C:\\OutputPosts\\";
        String[]arguments = {url,directory};
        ArgumentConverter argumentConverter = new ArgumentConverter();
        Map<String,String> map = argumentConverter.mapArguments(arguments);


        assertAll(() -> map.containsKey("url"),
                  () -> map.containsValue("https://jsonplaceholder.typicode.com/posts"),
                  () -> map.containsKey("directory"),
                  () -> map.containsKey("C:\\OutputPosts")

        );

    }

}

