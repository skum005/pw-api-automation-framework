package utils.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

public class YamlParser {

    public <T> T parseYML(String yamlFile, Class<T> serializationType) {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(new File(yamlFile), serializationType);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to convert given YAML to Object. " + exception.getMessage());
        }
    }

}
