package converters;

import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@NoArgsConstructor
public class ArgumentConverter {
    private static final  String defaultDirectory ="C:\\Output\\";
    private Map<String, String> stringArguments = new HashMap<>();
    public Map<String,String> mapArguments(String[] args) {
      for (String argument:args) {
          if (!argument.contains("=")) {
              throw new IllegalArgumentException("Invalid argument format: " + argument);
          }
            String key = argument.substring(0,argument.indexOf("="));
            String value = argument.substring(argument.indexOf("=")+1);
            stringArguments.put(key,value);
        }
        return stringArguments;
    }

    public String getDirectory() {
        return Optional.of(stringArguments.get("directory")).orElse(defaultDirectory);
    }

    public String getUrl() {
        return Optional.of( stringArguments.get("url")).orElseThrow(() -> new IllegalArgumentException("Empty url passed as argument"));
    }

    public String getType(){
        return Optional.of(stringArguments.get("type")).orElseThrow(() -> new IllegalArgumentException("Empty type passed as argument"));
    }

}
