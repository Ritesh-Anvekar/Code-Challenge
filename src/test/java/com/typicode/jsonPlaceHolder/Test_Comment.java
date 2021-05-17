package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import java.util.List;

@Feature("JsonPlaceHolder > Comment > Validations")
class Test_Comment extends Suite_SetUp {

    private static Logger logger = LogManager.getLogger(Test_Comment.class);
    private SoftAssertions softAssertions = new SoftAssertions();

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
