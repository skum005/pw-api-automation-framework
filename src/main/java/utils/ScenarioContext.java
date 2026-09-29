package utils;

import com.microsoft.playwright.APIResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScenarioContext {

    private String scenarioName;
    private String screenshotPath;
    private APIResponse response;

}
