package utils.env;

import utils.parsers.YamlParser;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigReader {

    public static final Path API_CONFIG_YAML_PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "api.config.yaml");
    private YamlParser yamlParser;
    private EnvironmentConfig environmentConfig;

    public ConfigReader(YamlParser yamlParser) {
        this.yamlParser = yamlParser;
        this.environmentConfig = yamlParser.parseYML(API_CONFIG_YAML_PATH.toString(), EnvironmentConfig.class);
    }

    public String getEnvironment() {
        String environment = System.getProperty("ENV");
        return environment = environment == null ? environmentConfig.getDefaultEnv() : environment;
    }

    public String getBaseURL(String apiName) {
        String environment = getEnvironment();
        try {
            return environmentConfig.getEnvironments().get(environment).get(apiName).getBase_url();
        } catch (Exception exception) {
            throw new RuntimeException("Failed get config(Base URL) for the given API: " + apiName + ". " + exception.getMessage());
        }
    }

    public String getBasePath(String apiName, String endpointName) {
        String environment = getEnvironment();
        try {
            return environmentConfig.getEnvironments().get(environment).get(apiName).getEndpoints().get(endpointName);
        } catch (Exception exception) {
            throw new RuntimeException("Failed get config(Endpoint path) for the given API: " + apiName + ". " + exception.getMessage());
        }
    }

}