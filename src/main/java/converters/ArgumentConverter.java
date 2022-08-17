package converters;

import java.util.HashMap;
import java.util.Map;

public class ArgumentConverter {
    private ArgumentConverter(){}
    private static Map<String, String> stringArguments = new HashMap<>();
    public static Map<String,String> mapArguments(String[] args) {

        for (String argument:args) {
            String key = argument.substring(0,argument.indexOf("="));
            String value = argument.substring(argument.indexOf("=")+1);
            stringArguments.put(key,value);
        }
        return stringArguments;
    }

    public static String getDirectory() {
        return stringArguments.get("directory");
    }

    public static String getUrl() {
        return stringArguments.get("url");
    }


}
