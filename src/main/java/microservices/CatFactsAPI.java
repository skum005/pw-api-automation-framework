package microservices;

import com.microsoft.playwright.APIResponse;
import utils.RestClient;

import java.util.Map;

public class CatFactsAPI extends RestClient {

    private String baseURL;

    public CatFactsAPI(String baseURL) {
        this.baseURL = baseURL;
    }

    public APIResponse getCatFact(String basePath, Map<String, String> params) {
        return setConfig(baseURL)
                .setQueryParams(params)
                .setHeaders(getJSONContentTypeHeader())
                .submitRequest(basePath, RequestType.GET);
    }

    public APIResponse getBreeds(String basePath, Map<String, String> params) {
        return setConfig(baseURL)
                .setQueryParams(params)
                .setHeaders(getJSONContentTypeHeader())
                .submitRequest(basePath, RequestType.GET);
    }

}
