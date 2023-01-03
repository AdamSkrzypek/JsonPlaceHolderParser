package converters;



import java.util.HashMap;
import java.util.Map;



public class ArgumentConverter {
    public ArgumentConverter(){

    }
    private Map<String, String> stringArguments = new HashMap<>();
    public Map<String,String> mapArguments(String[] args) {

        for (String argument:args) {
            String key = argument.substring(0,argument.indexOf("="));
            String value = argument.substring(argument.indexOf("=")+1);
            stringArguments.put(key,value);
        }
        return stringArguments;
    }

    public String getDirectory() {
        return stringArguments.get("directory");
    }

    public String getUrl() {
        return stringArguments.get("url");
    }

    public String getType(){
        return stringArguments.get("type");
    }

}
