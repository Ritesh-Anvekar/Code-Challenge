package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import restAssured.RestExecutor;
import java.util.List;

class Post extends RestExecutor {

    private static Logger logger = LogManager.getLogger(Post.class);
    private static final String POST_BASEPATH = "/posts";
    private static final String POST_USERID = "userId";
    private static final String POST_ID = "id";
    private static final String POST_TITLE = "title";
    private static final String POST_BODY = "body";

    Post(){
        super();
        requestSpecBuilder.setBasePath(POST_BASEPATH);
    }

    @Step("Fetch Posts(as Response) for User Id : {0}")
    Response fetchPosts(Integer iUserId) {
        logger.debug("-- fetchPosts() --");
        requestSpecBuilder.addQueryParam(POST_USERID, iUserId);
        return this.executeRequest(requestSpecBuilder.build(), "GET");
    }

    @Step("Extract Post Ids from (Response)Post : {0}")
    List<Integer> extractPostIds(Response responsePosts) {
        logger.debug("-- extractPostIds() --");
        return responsePosts.jsonPath().getList(POST_ID);
    }

}
