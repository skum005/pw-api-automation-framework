package utils;

import utils.env.ConfigReader;
import utils.parsers.JSONParser;
import utils.parsers.YamlParser;

public class UtilManager {

    private JSONParser jsonParser;
    private YamlParser yamlParser;
    private ConfigReader configReader;

    public JSONParser getJsonParser() {
        return jsonParser = jsonParser == null ? new JSONParser() : jsonParser;
    }

    public YamlParser getYamlParser() {
        return yamlParser = yamlParser == null ? new YamlParser() : yamlParser;
    }

    public ConfigReader getConfigReader(YamlParser yamlParser) {
        return configReader = configReader == null ? new ConfigReader(yamlParser) : configReader;
    }
}
