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

@Feature("JsonPlaceHolder > Comment > Validations")
class TestBlock_Comment {

    private static Logger logger = LogManager.getLogger(TestBlock_Comment.class);
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

    @Test
    @Description("Validate Email format for each Comments in the Post Id '81'")
    void IsEmailFormatCorrect() {
        logger.debug("-- IsEmailFormatCorrect() --");
        List<String> sEmails = comment.extractEmailsFromComments(comment.fetchComments(81));
        logger.info("Starting to validate Email Format.....");

        sEmails.forEach(sEmail->softAssertions.assertThat(comment.validateEmailFormat(sEmail)).isTrue());
        softAssertions.assertAll();
    }

}
