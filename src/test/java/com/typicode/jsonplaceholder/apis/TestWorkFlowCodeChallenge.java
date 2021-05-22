package com.typicode.jsonplaceholder.apis;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

@Epic("JsonPlaceHolder > Code Challenge")
class TestWorkFlowCodeChallenge extends WorkFlowInitializer {

    private static Logger logger = LogManager.getLogger(TestWorkFlowCodeChallenge.class);

    @Order(1)
    @Feature("Email Format Should Be Compliant")
    @Description("To perform the email format validations in the comments for the post made by a specific user")
    @ParameterizedTest(name = "Validate Email Format for each Posted Comments by User Name : {0}")
    @ValueSource(strings = {"Delphine", "Antonette", "Samantha"})
    @DisplayName("Validate Emails In The Each Posted Comments For Specified User")
    void validateEmailsInTheEachPostedCommentsForSpecifiedUser(String sUserName) {
        logger.debug("-- validateEmailsInTheEachPostedCommentsForSpecifiedUser() --");

        logger.debug("Starting to extract User Id for User Name: '"+sUserName+"'.....");
        List<String> iUserId = user.extractUserAttribute(user.USER_ID, user.fetchUserDetails(user.USER_USERNAME, sUserName));
        assertThat(iUserId.size())
                .overridingErrorMessage("Expected One User Id, But we found '%s'", iUserId.size())
                .isEqualTo(1);

        logger.debug("Starting to extract Posts made by User Id: '"+iUserId+"'.....");
        List <Integer> iPostIds = post.extractPostIds(post.fetchPosts(String.valueOf(iUserId.get(0))));

        for (Integer iPostId : iPostIds) {
            logger.debug("Starting to extract Emails from each comments for Post Id: '"+iPostId+"'.....");
            List <String> sEmails = comment.extractEmailsFromComments(comment.fetchComments(iPostId));

            assertThat(sEmails)
                    .overridingErrorMessage("Expected Emails, But we found None")
                    .isNotNull();

            logger.debug("Starting to validate Email Format.....");
            sEmails.forEach(sEmail->softAssertions.assertThat(comment.validateEmailFormat(sEmail)).describedAs("Validating Email: "+sEmail).isTrue());
        }
        softAssertions.assertAll();
    }

}
