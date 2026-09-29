package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ScenarioContext;
import utils.TestContext;

public class Hooks {

    private Scenario scenario;
    private TestContext testContext;
    private ScenarioContext scenarioContext;
    private Logger logger = LogManager.getLogger(this);

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
        this.scenarioContext = this.testContext.getScenarioContext();
    }

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        scenarioContext.setScenarioName(trimScenarioName(this.scenario));
        logger.info("Executing scenario {}", scenarioContext.getScenarioName());
    }

    @After
    public void tearDown() {
        if(scenario.isFailed()) {
            logger.error("Scenario {} is failed", scenarioContext.getScenarioName());
            // implement code for taking screenshot/capturing some info when a test is failed
        }
        try {
            // tear down code like closing DB connections etc.
            logger.info("Executing tear down code for {}", scenarioContext.getScenarioName());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to close browsers. " + exception.getMessage());
        }
    }

    private String trimScenarioName(Scenario scenario) {
        String scenarioName = scenario.getName();
        if(scenarioName.contains(";")) {
            scenarioName = scenarioName.split(";")[0].trim();
        }
        if(scenarioName.length() > 15)
            scenarioName = scenarioName.substring(0,14);
        return scenarioName;
    }
}
