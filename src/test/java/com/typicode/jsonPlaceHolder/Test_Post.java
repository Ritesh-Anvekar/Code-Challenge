package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import java.util.List;

@Feature("JsonPlaceHolder > Post > Validations")
class Test_Post {

    private static Logger logger = LogManager.getLogger(Test_Post.class);

    @Test
    @Description("Validate whether the User Id '9' has 10 Posts")
    void fetchPosts() {
        logger.debug("-- fetchPosts() --");
        Post post = new Post();
        List<Integer> iPostId = post.fetchPostIds(post.fetchPosts(9));
        SoftAssertions.assertSoftly(softly ->
                softly.assertThat(iPostId.size())
                        .overridingErrorMessage("Expected 10 Post Ids, But we found '%s'", iPostId.size())
                        .isEqualTo(10));
    }

}
