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

    // attributes
    final String USER_ID = "id";
    final String USER_NAME = "name";
    final String USER_USERNAME = "username";
    final String USER_EMAIL = "email";
    final String USER_ADDRESS = "address";
    final String USER_PHONE = "phone";
    final String USER_WEBSITE = "website";
    final String USER_COMPANY = "company";

    private static Logger logger = LogManager.getLogger(User.class);
    private static String USER_BASE_PATH = util_Properties.loadProperties(util_Properties.APPLICATIONS).getProperty("BASEPATH_USER");

    User(){
        super();
        this.requestSpecBuilder.setBasePath(USER_BASE_PATH);
    }

    @Step("Fetch Users(as Response)")
    Response fetchUsers() {
        logger.debug("-- fetchUsers() --");
        return this.executeRequest(requestSpecBuilder.build(), "GET");
    }

    @Step("Fetch User Details(as Response) by applying Query Parameter > {0}:{1}")
    Response fetchUserDetails(String sAttributeKey, String sAttributeValue) {
        logger.debug("-- fetchUserDetailsByQueryParams() --");
        requestSpecBuilder.addQueryParam(sAttributeKey, sAttributeValue);
        return fetchUsers();
    }

    @Step("Extract User Attribute {0} from (Response)UserDetails : {0}")
    List <String> extractUserAttribute(String sAttribute, Response responseUserDetails) {
        logger.debug("-- extractUserAttribute() : '"+sAttribute+"' --");
        if(responseUserDetails.jsonPath().get(sAttribute).getClass().getTypeName().contains("List")) {
            return responseUserDetails.jsonPath().get(sAttribute);
        } else {
            return Collections.singletonList(responseUserDetails.jsonPath().get(sAttribute));
        }
    }

    @Step("Extract Last Allocated UserId")
    String extractLastAllocatedUserId() {
        logger.debug("-- extractLastAllocatedUserId() --");
        return String.valueOf(Collections.max(extractUserAttribute(USER_ID, fetchUsers())));
    }

    @Step("Add User with UserName {0}")
    Response addUser(String sUserName){
        logger.debug("-- addUser() --");
        requestSpecBuilder.setBody(new File(getDataPath(Collections.singletonList("User")) +sUserName +defaultDataFileType));
        return executeRequest(requestSpecBuilder.build(), "POST");
    }

    @Step("Delete User with User Id {0}")
    Response deleteUser(String sUserId){
        logger.debug("-- deleteUser() --");
        requestSpecBuilder.setBasePath(USER_BASE_PATH +sUserId);
        return executeRequest(requestSpecBuilder.build(), "DELETE");
    }

}

