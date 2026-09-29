package utils.env;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class APIConfig {

    String base_url;
    Map<String, String> endpoints;

}
