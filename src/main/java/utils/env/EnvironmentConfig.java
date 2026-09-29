package utils.env;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class EnvironmentConfig {

    private Map<String, Map<String, APIConfig>> environments;
    private String defaultEnv;

}
