package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import restAssured.RestExecutor;
import util.util_Properties;
import java.io.File;
import java.util.Collections;
import java.util.List;

class User extends RestExecutor {

    private static Logger logger = LogManager.getLogger(User.class);
    private static final String USER_ID = "id";
    private static final String USER_NAME = "name";
    private static final String USER_USERNAME = "username";
    private static final String USER_EMAIL = "email";
    private static final String USER_ADDRESS = "address";
    private static final String USER_PHONE = "phone";
    private static final String USER_WEBSITE = "website";
    private static final String USER_COMPANY = "company";

    User(){
        super();
        requestSpecBuilder.setBasePath(util_Properties.loadProperties(util_Properties.APPLICATIONS).getProperty("BASEPATH_USER"));
    }

    @Step("Fetch User Details(as Response) for User Name: {0}")
    Response fetchUserDetails(String sUserName) {
        logger.debug("-- fetchUserDetails() --");
        requestSpecBuilder.addQueryParam(USER_USERNAME, sUserName);
        return this.executeRequest(requestSpecBuilder.build(), "GET");
    }

    @Step("Extract UserId from (Response)UserDetails : {0}")
    List<Integer> extractUserIds(Response responseUserDetails) {
        logger.debug("-- extractUserIds() --");
        return responseUserDetails.jsonPath().get(USER_ID);
    }

    @Step("Add User with UserName {0}")
    Response addUser(String sUserName){
        logger.debug("-- addUser() --");
        requestSpecBuilder.setBody(new File(getDataPath(Collections.singletonList("User")) +sUserName +defaultDataFileType));
        return executeRequest(requestSpecBuilder.build(), "POST");
    }
}

