package microservices;

import com.microsoft.playwright.APIResponse;
import utils.RestClient;

import java.util.Map;

public class HumanResourcesAPI extends RestClient {

    private String baseURL;

    public HumanResourcesAPI(String baseURL) {
        this.baseURL = baseURL;
    }

    public APIResponse createEmployee(String basePath, Map<String, String> headers, String body) {
        headers.putAll(getJSONContentTypeHeader());
        return setConfig(baseURL).setHeaders(headers).setBody(body).submitRequest(basePath, RequestType.POST);
    }

}
