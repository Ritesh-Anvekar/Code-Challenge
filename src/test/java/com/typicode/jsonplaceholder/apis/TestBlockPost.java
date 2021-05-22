package com.typicode.jsonplaceholder.apis;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Epic("JsonPlaceHolder > Post > Validations")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestBlockPost {

    private static Logger logger = LogManager.getLogger(TestBlockPost.class);
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

    @Feature("Verify No Of Posts")
    @Description("Verify whether the User Id 'X' has 'Y' No Of Posts")
    @ParameterizedTest(name = "Verify [For User Id, Expected No Of Posts]: {0}")
    @MethodSource("verifyNoOfPosts")
    @DisplayName("Verify No Of Posts")
    void verifyNoOfPosts(List<String> listUserIdAndPostsMade) {
        logger.debug("-- verifyNoOfPosts() --");
        List<Integer> iPostId = post.extractPostIds(post.fetchPosts(listUserIdAndPostsMade.get(0)));
        assertThat(iPostId.size())
                .overridingErrorMessage("Expected 10 Post Ids, But we found '%s'", iPostId.size())
                .isEqualTo(listUserIdAndPostsMade.get(1));
    }

    static Stream<Arguments> verifyNoOfPosts() {
        return Stream.of(
                arguments(Arrays.asList("8", 10)),
                arguments(Arrays.asList("10", 10))
        );
    }
}
