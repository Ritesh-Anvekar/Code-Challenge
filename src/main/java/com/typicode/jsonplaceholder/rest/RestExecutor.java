package com.typicode.jsonplaceholder.rest;

import com.typicode.jsonplaceholder.util.ExceptionUtil;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class RestExecutor extends RestDataHandler {

    private static Logger logger = LogManager.getLogger(RestExecutor.class);
    public Response mResponse;

    public RestExecutor() {
        super();
    }

    @Step("RestExecutor] Perform {1} on Request {0}")
    protected Response executeRequest(RequestSpecification pRequestSpec, String pMethod) {
        logger.debug("-- [RestExecutor] executeRequest() --");
        try {
            RequestSender requestSender = RestAssured.given(pRequestSpec).filter(new AllureRestAssured()).when();
            mResponse = requestSender.request(pMethod);
            return mResponse;
        } catch(Exception Ex){
            throw new ExceptionUtil("Request Execution Failed !! \nException Message:\n" +Ex, Ex);
        }
    }

}
