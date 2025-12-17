//Create a `ConfigManager` class using static inner class approach for Singleton
//- `ConfigManager` should include:
//  - `private static class ConfigHolder`
//  - `private ConfigManager()` - private constructor
//  - `public static ConfigManager getInstance()`
//  - `private Map<String, String> configs` - initialize in constructor
//  - `public void setConfig(String key, String value)`
//  - `public String getConfig(String key)`
//  - `public void displayAllConfigs()`

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private Map<String, String> configs;

    private ConfigManager(){
        configs = new HashMap<>();
    }
    private static class ConfigHolder{
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    public static ConfigManager getInstance(){
        return ConfigHolder.INSTANCE;
    }

    public void setConfig(String key, String value){
        configs.put(key,value);
    }

    public void displayAllConfigs(){
        for (Map.Entry<String, String> entry : configs.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

}
