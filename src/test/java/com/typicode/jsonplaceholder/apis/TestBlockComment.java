package com.typicode.jsonplaceholder.apis;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;

@Epic("JsonPlaceHolder > Comment > Validations")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestBlockComment {

    private static Logger logger = LogManager.getLogger(TestBlockComment.class);
    private SoftAssertions softAssertions = new SoftAssertions();
    private Comment comment;

    @BeforeEach
    @Description("Set Up")
    void setUp(){
        comment = new Comment();
    }

    @AfterEach
    @Description("Tear Down")
    void tearDown(){
        this.comment.mResponse = null;
    }

    @Order(1)
    @Feature("Email Format Should Be Compliant")
    @Description("Validate whether the Email format in compliance for each Comments in the Post Id 'X'")
    @ParameterizedTest(name = "Validate Email Format for each Comments in the Post Id : {0}")
    @ValueSource(ints = {81, 88})
    @DisplayName("Validate Email Format for each Comments")
    void IsEmailFormatCorrect(Integer iPostId) {
        logger.debug("-- IsEmailFormatCorrect() --");
        List<String> sEmails = comment.extractEmailsFromComments(comment.fetchComments(iPostId));
        logger.info("Starting to validate Email Format.....");

        sEmails.forEach(sEmail->softAssertions.assertThat(comment.validateEmailFormat(sEmail)).isTrue());
        softAssertions.assertAll();
    }

}
