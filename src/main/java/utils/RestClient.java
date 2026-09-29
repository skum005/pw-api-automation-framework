package utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import microservices.RequestType;

import java.util.HashMap;
import java.util.Map;

public class RestClient {

    public APIRequestContext apiRequestContext;
    public RequestOptions apiRequestOptions;
    public APIRequest.NewContextOptions apiRequestContextOptions;

    public RestClient() {
        apiRequestOptions = RequestOptions.create();
    }

    public RestClient setConfig(String baseURL) {
        try {
            Playwright playwright = Playwright.create();
            APIRequest request = playwright.request();
            apiRequestContextOptions = new APIRequest.NewContextOptions();
            apiRequestContextOptions = apiRequestContextOptions.setBaseURL(baseURL);
            apiRequestContext = request.newContext(apiRequestContextOptions);
            return this;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error occurred while generating request spec with baseURL and basePath");
        }
    }

    public RestClient setQueryParams(Map<String, String> params) {
        try {
            params.forEach((key, value) -> apiRequestOptions.setQueryParam(key, value));
            return this;
        } catch (Exception exception) {
            throw new RuntimeException("Error occurred while generating request spec with query parameters");
        }
    }

    public RestClient setHeaders(Map<String, String> headers) {
        try {
            headers.forEach((key, value) -> apiRequestOptions.setHeader(key, value));
            return this;
        } catch (Exception exception) {
            throw new RuntimeException("Error occurred while generating request spec with headers");
        }
    }

    public RestClient setBody(String body) {
        try {
            apiRequestOptions.setData(body);
            return this;
        } catch (Exception exception) {
            throw new RuntimeException("Error occurred while generating request spec with headers");
        }
    }

    public RestClient setBasicAuth(String username, String password) {
        try {
            apiRequestContextOptions.setHttpCredentials(username, password);
            return this;
        } catch (Exception exception) {
            throw new RuntimeException("Error occurred while generating request spec with basic authentication");
        }
    }

    public APIResponse submitRequest(String path, RequestType requestType) {
        try {
            switch (requestType) {
                case GET:
                    return apiRequestContext.get(path, apiRequestOptions);
                case POST:
                    return apiRequestContext.post(path, apiRequestOptions);
                case PUT:
                    return apiRequestContext.put(path, apiRequestOptions);
                case DELETE:
                    return apiRequestContext.delete(path, apiRequestOptions);
                default:
                    throw new RuntimeException("Unsupported request type provided");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error occurred while submitting the request");
        }
    }

    public Map<String, String> getJSONContentTypeHeader() {
        return new HashMap<>(Map.of(
                "Content-Type", "application/json"
        ));
    }

}
