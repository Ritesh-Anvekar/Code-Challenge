package com.typicode.jsonPlaceHolder;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import restAssured.RestExecutor;
import util.util_Basic;
import java.util.List;

class Comment extends RestExecutor {

    private static Logger logger = LogManager.getLogger(Comment.class);
    private static final String COMMENT_BASEPATH = "/comments";
    private static final String COMMENT_POSTID = "postId";
    private static final String COMMENT_ID = "id";
    private static final String COMMENT_NAME = "name";
    private static final String COMMENT_EMAIL = "email";
    private static final String COMMENT_BODY = "body";

    Comment(){
        super();
        this.requestSpecBuilder.setBasePath(COMMENT_BASEPATH);
    }

    Response fetchComments(Integer iPostId) {
        logger.debug("-- fetchComments() --");
        requestSpecBuilder.addQueryParam(COMMENT_POSTID, iPostId);
        return this.executeRequest(requestSpecBuilder.build(), "GET");
    }

    List <String> fetchEmailsFromComments(Response responseComments) {
        logger.debug("-- fetchEmailsFromComments() --");
        return responseComments.jsonPath().getList(COMMENT_EMAIL);
    }

    boolean IsEmailFormatCorrect(String sEmail) {
        logger.debug("-- IsEmailFormatCorrect() --");
        return util_Basic.regExPatternMatcher(sEmail,"^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }


}
