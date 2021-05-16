package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import java.util.List;

@Feature("JsonPlaceHolder > Comment > Validations")
class Test_Comment {

    private SoftAssertions softAssert;
    private static Logger logger = LogManager.getLogger(Test_Comment.class);

    Test_Comment() {
        this.softAssert = new SoftAssertions();;
    }

    @Test
    @Description("Validate Email format for each Comments in the Post Id '81'")
    void IsEmailFormatCorrect() {
        logger.debug("-- IsEmailFormatCorrect() --");
        Comment comment = new Comment();
        List<String> sEmails = comment.fetchEmailsFromComments(comment.fetchComments(81));
        logger.info("Starting to validate Email Format.....");
        sEmails.forEach(sEmail ->
                SoftAssertions.assertSoftly(softly ->
                        softly.assertThat(comment.IsEmailFormatCorrect(sEmail)).isTrue()));
    }

}
