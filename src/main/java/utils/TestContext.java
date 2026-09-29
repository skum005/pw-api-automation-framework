package utils;

import lombok.Getter;

@Getter
public class TestContext {

    private ScenarioContext scenarioContext;
    private APIManager APIManager;
    private UtilManager utilManager;

    public TestContext() {
        scenarioContext = new ScenarioContext();
        APIManager = new APIManager();
        utilManager = new UtilManager();
    }

}
