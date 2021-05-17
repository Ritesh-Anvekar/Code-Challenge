package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import restAssured.RestExecutor;
import util.util_Basic;
import util.util_Properties;
import java.util.List;

class Comment extends RestExecutor {

    private static Logger logger = LogManager.getLogger(Comment.class);
    private static final String COMMENT_POSTID = "postId";
    private static final String COMMENT_ID = "id";
    private static final String COMMENT_NAME = "name";
    private static final String COMMENT_EMAIL = "email";
    private static final String COMMENT_BODY = "body";

    Comment(){
        super();
        requestSpecBuilder.setBasePath(util_Properties.loadProperties(util_Properties.APPLICATIONS).getProperty("BASEPATH_COMMENT"));
    }

    @Step("Fetch Comments(as Response) for Post Id: {0}")
    Response fetchComments(Integer iPostId) {
        logger.debug("-- fetchComments() --");
        requestSpecBuilder.removeQueryParam(COMMENT_POSTID);
        requestSpecBuilder.addQueryParam(COMMENT_POSTID, iPostId);
        return this.executeRequest(requestSpecBuilder.build(), "GET");
    }

    @Step("Extract Emails from (Response)Comments : {0}")
    List <String> extractEmailsFromComments(Response responseComments) {
        logger.debug("-- extractEmailsFromComments() --");
        return responseComments.jsonPath().getList(COMMENT_EMAIL);
    }

    @Step("Validate Format for Email : {0}")
    boolean validateEmailFormat(String sEmail) {
        logger.debug("-- validateEmailFormat() --");
        return util_Basic.regExPatternMatcher(sEmail,"^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }


}
