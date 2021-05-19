package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

@Feature("JsonPlaceHolder > Code Challenge")
class TestWorkFlow_CodeChallenge extends WorkFlow_SetUp {

    private static Logger logger = LogManager.getLogger(TestWorkFlow_CodeChallenge.class);

    @Test
    @Description("To perform the validations for the comments for the post made by a specific user")
    void validateEmailsInTheEachPostedCommentsForSpecifiedUser() {
        logger.debug("-- validateEmailsInTheEachPostedCommentsForSpecifiedUser() --");

        String sUserName = "Delphine";
        logger.info("Starting to extract User Id for User Name: '"+sUserName+"'.....");
        List<String> iUserId = user.extractUserAttribute(user.USER_ID, user.fetchUserDetails(user.USER_USERNAME, sUserName));
        assertThat(iUserId.size())
                .overridingErrorMessage("Expected One User Id, But we found '%s'", iUserId.size())
                .isEqualTo(1);

        logger.info("Starting to extract Posts made by User Id: '"+iUserId+"'.....");
        List <Integer> iPostIds = post.extractPostIds(post.fetchPosts(String.valueOf(iUserId.get(0))));

        for (Integer iPostId : iPostIds) {
            logger.info("Starting to extract Emails from each comments for Post Id: '"+iPostId+"'.....");
            List <String> sEmails = comment.extractEmailsFromComments(comment.fetchComments(iPostId));

            assertThat(sEmails)
                    .overridingErrorMessage("Expected Emails, But we found None")
                    .isNotNull();

            logger.info("Starting to validate Email Format.....");
            sEmails.forEach(sEmail->softAssertions.assertThat(comment.validateEmailFormat(sEmail)).describedAs("Validating Email: "+sEmail).isTrue());
        }
        softAssertions.assertAll();
    }

}
