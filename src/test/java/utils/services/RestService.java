package utils.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class RestService {
    private static final String BASE_URL = "http://localhost:8080/api";
    protected String token;
    protected RequestSpecification REQ_SPEC;

    protected abstract String getBasePath();

    public RestService(String token) {
        this.token = token;

        REQ_SPEC = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setBaseUri(BASE_URL)
                .setBasePath(getBasePath())
                .setContentType(ContentType.JSON)
                .build();
    }
}
