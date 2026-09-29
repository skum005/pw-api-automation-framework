package stepdefinitions;

import com.microsoft.playwright.APIResponse;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import microservices.HumanResourcesAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import payloads.human_resources.Employee;
import utils.TestContext;

import java.util.HashMap;
import java.util.Map;

public class HumanResourcesAPIStepDefinition extends BaseStepDefinitions {

    private HumanResourcesAPI humanResourcesAPI;
    private final String baseURL;
    private Logger logger = LogManager.getLogger(this);
    private Map<String, String> authHeader;

    public HumanResourcesAPIStepDefinition(TestContext testContext) {
        super(testContext);
        baseURL = configReader.getBaseURL("human-resources-api");
        humanResourcesAPI = apiManager.getHumanResourcesAPI(baseURL);
    }

    @DataTableType
    public Employee createEmployeeRequestBody(Map<String, String> data) {
        return new Employee(data.get("NAME"), data.get("JOB"));
    }

    @When("a request is submitted to create a HR")
    public void submitCreateEmployeeRequest(Employee employee) {
        String basePath = configReader.getBasePath("human-resources-api", "create_employee");
        String body = jsonParser.serializeToJSON(employee);
        Allure.addAttachment("Create Employee Request Body", body);
        APIResponse response = humanResourcesAPI.createEmployee(basePath, authHeader, body);
        scenarioContext.setResponse(response);
    }

    @Given("HR has required access to HR API")
    public void extractAPIKey() {
        logger.info("Extracting API key from environment variables..");
        authHeader = new HashMap<>(Map.of("x-api-key", "reqres-free-v1"));
    }

    @Then("employee should be created successfully")
    public void validateEmployeeCreation() {
        Assert.assertNotNull(scenarioContext.getResponse(), "Response must be saved in Scenario Context");
        Allure.addAttachment("Create Employee Response Body", scenarioContext.getResponse().text());
        Assert.assertEquals(scenarioContext.getResponse().status(), 201, "Response status code did not match");
    }
}
