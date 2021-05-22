package com.typicode.jsonplaceholder.apis;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.typicode.jsonplaceholder.rest.RestExecutor;
import com.typicode.jsonplaceholder.util.PropertiesUtil;
import java.util.List;

class Post extends RestExecutor {

    // attributes
    private static final String POST_USERID = "userId";
    private static final String POST_ID = "id";
    private static final String POST_TITLE = "title";
    private static final String POST_BODY = "body";

    private static Logger logger = LogManager.getLogger(Post.class);
    private static String POST_BASE_PATH = PropertiesUtil.loadProperties(PropertiesUtil.APPLICATIONS).getProperty("BASEPATH_POST");

    Post(){
        super();
        requestSpecBuilder.setBasePath(POST_BASE_PATH);
    }

    @Step("Fetch Posts(as Response) for User Id : {0}")
    Response fetchPosts(String iUserId) {
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
