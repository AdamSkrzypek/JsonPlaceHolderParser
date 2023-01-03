package util;

import lombok.extern.slf4j.Slf4j;
import java.io.File;

@Slf4j
public class FileUtil {
    private FileUtil() {
    }

    public static boolean createDirectory(String directory) {
        File file = new File(directory);
        if (file.mkdir()){
            log.info(" new output directory is created: " + directory);
        }
        log.info("output directory: " + directory);
        return false;
    }
}
