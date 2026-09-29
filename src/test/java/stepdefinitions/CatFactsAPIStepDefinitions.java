package stepdefinitions;

import com.microsoft.playwright.APIResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import microservices.CatFactsAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import payloads.catfacts.CatFactResponse;
import utils.TestContext;

import java.util.HashMap;
import java.util.Map;

public class CatFactsAPIStepDefinitions extends BaseStepDefinitions {

    private CatFactsAPI catFactsAPI;
    private final String baseURL;
    private Logger logger = LogManager.getLogger(this);

    public CatFactsAPIStepDefinitions(TestContext testContext) {
        super(testContext);
        baseURL = configReader.getBaseURL("cat-facts-api");
        catFactsAPI = new CatFactsAPI(baseURL);
    }

    @Given("User has access to cat facts API")
    public void userHasAccessToCatFactsAPI() {
        //Fetch any API KEYs from the environment variables/secret files
        logger.info("Extracting authentication details for the given API");
    }

    @When("User queries for a cat fact with a char limit of {int}")
    public void userQueriesForACatFact(int limit) {
        Map<String, String> queryParams = new HashMap<>(Map.of("max_length", String.valueOf(limit)));
        String basePath = configReader.getBasePath("cat-facts-api", "facts");
        APIResponse response = catFactsAPI.getCatFact(basePath, queryParams);
        scenarioContext.setResponse(response);
    }

    @When("User queries for a cat breeds with a limit of {int}")
    public void queryBreeds(int limit) {
        Map<String, String> queryParams = new HashMap<>(Map.of("limit", String.valueOf(limit)));
        String basePath = configReader.getBasePath("cat-facts-api", "breeds");
        APIResponse response = catFactsAPI.getCatFact(basePath, queryParams);
        scenarioContext.setResponse(response);
    }

    @Then("a cat fact should be returned")
    public void validateCatFacts() {
        Assert.assertNotNull(scenarioContext.getResponse(), "Response is not captured in Scenario Context");
        CatFactResponse catFactResponse = jsonParser.deserializeJSONToJavaObject(scenarioContext.getResponse().text(), CatFactResponse.class);
        Allure.addAttachment(scenarioContext.getResponse().text(), "Cat facts response body");
        Assert.assertEquals(scenarioContext.getResponse().status(), 200, "API Response code did not match");
        Assert.assertNotNull(catFactResponse.getFact(), "\"fact\" in the response doesn't have any value");
        Assert.assertTrue(catFactResponse.getLength() > 0, "\"length\" in the response body should be great than 0");
    }


    @Then("cat breeds should be returned")
    public void validateCatBreds() {
        Assert.assertNotNull(scenarioContext.getResponse(), "Response is not captured in Scenario Context");
        Allure.addAttachment(scenarioContext.getResponse().text(), "Cat breeds response body");
        Assert.assertEquals(scenarioContext.getResponse().status(), 200, "API Response code did not match");
        //Assert.assertEquals(scenarioContext.getResponse().jsonPath().getList("data").size(), 10, "Response body doesn't have expected number of breeds");
    }

}
