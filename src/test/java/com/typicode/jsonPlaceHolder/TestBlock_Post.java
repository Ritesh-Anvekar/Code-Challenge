package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

@Feature("JsonPlaceHolder > Post > Validations")
class TestBlock_Post {

    private static Logger logger = LogManager.getLogger(TestBlock_Post.class);
    private Post post;

    @BeforeEach
    @Description("Set Up")
    void setUp(){
        post = new Post();
    }

    @AfterEach
    @Description("Tear Down")
    void tearDown(){
        this.post.mResponse = null;
    }

    @Test
    @Description("Validate whether the User Id '9' has 10 Posts")
    void fetchPosts() {
        logger.debug("-- fetchPosts() --");
        List<Integer> iPostId = post.extractPostIds(post.fetchPosts("9"));
        SoftAssertions.assertSoftly(softly ->
                softly.assertThat(iPostId.size())
                        .overridingErrorMessage("Expected 10 Post Ids, But we found '%s'", iPostId.size())
                        .isEqualTo(10));
    }
}
