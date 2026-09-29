package stepdefinitions;

import utils.*;
import utils.env.ConfigReader;
import utils.parsers.JSONParser;

public class BaseStepDefinitions {

    protected TestContext testContext;
    protected ScenarioContext scenarioContext;
    protected UtilManager utilManager;
    protected ConfigReader configReader;
    protected APIManager apiManager;
    protected JSONParser jsonParser;

    public BaseStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
        this.scenarioContext = testContext.getScenarioContext();
        this.utilManager = testContext.getUtilManager();
        this.apiManager = testContext.getAPIManager();
        this.configReader = utilManager.getConfigReader(utilManager.getYamlParser());
        this.jsonParser = utilManager.getJsonParser();
    }

}
