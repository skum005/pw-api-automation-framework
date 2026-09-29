package utils;

import microservices.CatFactsAPI;
import microservices.HumanResourcesAPI;

public class APIManager {

    private CatFactsAPI catFactsAPI;
    private HumanResourcesAPI humanResourcesAPI;

    public CatFactsAPI getCatFactsAPI(String baseURL) {
        return catFactsAPI = catFactsAPI == null ? new CatFactsAPI(baseURL) : catFactsAPI;
    }

    public HumanResourcesAPI getHumanResourcesAPI(String baseURL) {
        return humanResourcesAPI = humanResourcesAPI == null ? new HumanResourcesAPI(baseURL) : humanResourcesAPI;
    }

}
