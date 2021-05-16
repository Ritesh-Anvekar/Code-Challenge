package com.typicode.jsonPlaceHolder;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import restAssured.RestExecutor;


import java.util.List;

public class User extends RestExecutor {

    private static Logger logger = LogManager.getLogger(User.class);

    private static final String USER_BASEPATH = "/users";
    private static final String USER_ID = "id";
    private static final String USER_NAME = "name";
    private static final String USER_USERNAME = "username";
    private static final String USER_EMAIL = "email";
    private static final String USER_ADDRESS = "address";
    private static final String USER_PHONE = "phone";
    private static final String USER_WEBSITE = "website";
    private static final String USER_COMPANY = "company";

    public User(){
        super();
        this.requestSpecBuilder.setBasePath(USER_BASEPATH);
    }

    public Response fetchUserDetails(String sUserName) {
        logger.debug("-- fetchUserDetails() --");
        requestSpecBuilder.addQueryParam(USER_USERNAME, sUserName);
        return this.executeRequest(requestSpecBuilder.build(), "GET");
    }

    public List<Integer> fetchUserId(Response responseUserDetails) {
        logger.debug("-- fetchUserId() --");
        return responseUserDetails.jsonPath().get(USER_ID);
    }

}

